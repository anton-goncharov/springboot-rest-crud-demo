package com.antongoncharov.paymentservice.api.v1;

import com.antongoncharov.paymentservice.domain.Payment;
import com.antongoncharov.paymentservice.dto.PaymentDTO;
import com.antongoncharov.paymentservice.dto.mapping.PaymentMapper;
import com.antongoncharov.paymentservice.dto.response.ApiResponse;
import com.antongoncharov.paymentservice.dto.response.ErrorResponse;
import com.antongoncharov.paymentservice.dto.response.InternalErrorResponse;
import com.antongoncharov.paymentservice.dto.response.SuccessResponse;
import com.antongoncharov.paymentservice.exception.NotFoundException;
import com.antongoncharov.paymentservice.service.IPaymentsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.stream.Collectors;

/**
 * REST controller for Payments domain entity.
 * For more user-friendly description see generated Swagger documentation: /swagger-ui.html
 *
 */
@RestController
@RequestMapping("/v1/payments")
public class PaymentsController {


    public static final String API_PATH = "/v1/payments";

    private final IPaymentsService IPaymentsService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public PaymentsController(IPaymentsService IPaymentsService) {
        this.IPaymentsService = IPaymentsService;
    }

    @ApiOperation(value = "listPayments", nickname = "List Payments")
    @GetMapping(value = "")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ApiResponse<Page<PaymentDTO>>> list(Pageable pageable) {
        try {
            Page<Payment> allPayments = IPaymentsService.getAllPayments(pageable);
            Page<PaymentDTO> dtos = allPayments.map(PaymentMapper.INSTANCE::paymentToPaymentDto);
            SuccessResponse<Page<PaymentDTO>> response = new SuccessResponse<>();
            response.setData(dtos);
            return ResponseEntity.ok().body(response.withSelfLink(API_PATH));
        } catch (Exception e) {
            LOG.error("Error getting list of payments", e);
            return internalError();
        }
    }

    @ApiOperation(value = "getPaymentById", nickname = "Get Payment by ID")
    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ApiResponse<PaymentDTO>> getOne(@PathVariable String id) {
        try {
            Payment payment = IPaymentsService.getPaymentByExternalId(id).orElseThrow(NotFoundException::new);
            return ResponseEntity.ok().body(new ApiResponse<>(PaymentMapper.INSTANCE.paymentToPaymentDto(payment)).withSelfLink(API_PATH));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error("Error getting a payment with ID = " + id, e);
            return internalError();
        }
    }

    @ApiOperation(value = "createPayment", nickname = "Create a new Payment")
    @PostMapping(value = "")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Created"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ApiResponse> create(@RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = PaymentMapper.INSTANCE.paymentDtoToPayment(paymentDTO);
            Payment created = IPaymentsService.createPayment(payment);
            String insertedId = created.getExternalId();
            return ResponseEntity.created(new URI(API_PATH + "/" + insertedId)).body(new SuccessResponse(insertedId).withSelfLink(API_PATH));
        } catch (ConstraintViolationException e) {
            // extract all constraint violation errors and compact them to a string
            String reason = e.getConstraintViolations().stream()
                    .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                    .collect(Collectors.joining("; "));
            // return HTTP 400
            return ResponseEntity.badRequest().body(new ErrorResponse(reason).withSelfLink(API_PATH));
        } catch (Exception e) {
            LOG.error("Error creating a new payment", e);
            return internalError();
        }
    }

    @ApiOperation(value = "updatePayment", nickname = "Update Existing Payment (replaces data)")
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = PaymentMapper.INSTANCE.paymentDtoToPayment(paymentDTO);
            Payment updated = IPaymentsService.updatePayment(payment, id);
            return ResponseEntity.ok().body(new SuccessResponse().withSelfLink(API_PATH));
        } catch (NotFoundException e) {
            // 404
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error("Error updating a payment with ID = " + id, e);
            return internalError();
        }
    }

    @ApiOperation(value = "deletePayment", nickname = "Delete Existing Payment")
    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            IPaymentsService.deletePayment(id);
            return ResponseEntity.ok().body(new SuccessResponse().withSelfLink(API_PATH));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error("Error deleting a payment with ID = " + id, e);
            return internalError();
        }
    }

    private ResponseEntity internalError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalErrorResponse().withSelfLink(API_PATH));
    }


}
