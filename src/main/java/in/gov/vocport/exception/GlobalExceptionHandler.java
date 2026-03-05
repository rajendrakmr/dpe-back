package in.gov.vocport.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpResponse;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(PosApiException.class)
	public ResponseEntity<Map<String, String>> handlePosApiException(PosApiException ex, RedirectAttributes redirectAttributes) {
		log.error("Exception caught globally:", ex); // Full log for debugging

		Map<String, String> result = Map.of("error", "A server-side error occurred. Please contact the support team.");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	@ExceptionHandler(PaymentProcessingException.class)
	public ResponseEntity handlePaymentException(PaymentProcessingException ex, RedirectAttributes redirectAttributes) {

		log.error("Exception caught globally:", ex); // Full log for debugging

		Map<String, String> result = Map.of("error", "A server-side error occurred. Please contact the support team.");


		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneric(Exception ex, RedirectAttributes redirectAttributes) {

		log.error("Exception caught globally:", ex); // Full log for debugging

		Map<String, String> result = Map.of("error", ex.getMessage());


		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
}