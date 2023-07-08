package plasma.airbnb.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import plasma.airbnb.dto.request.ProductRequest;
import plasma.airbnb.dto.request.RejectionReasonRequest;
import plasma.airbnb.dto.response.ApplicationResponse;
import plasma.airbnb.reposiroty.UserRepository;
import plasma.airbnb.service.ApplicationService;

/**
 * Created by mouflon on 06.07.2023.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

    private final UserRepository userRepository;
    private final ApplicationService applicationService;

    @GetMapping(value = "/{applicationId}")
    @Operation(summary = "Get application by ID", description = "Retrieves application details based on the provided ID")
    public ApplicationResponse getApplication(@PathVariable Long applicationId) {
        return applicationService.getApplicationById(applicationId);
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new application", description = "Creates a new application based on the provided data")
//    @PreAuthorize("hasAnyAuthority('USER')")
    // Authorization doesn't work, fix it!
    public ApplicationResponse createApplication(@RequestBody ProductRequest productRequest) {
        // Find an authorized user by email and insert his id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return applicationService.createApplication(userRepository.findByEmail(username).get().getId(), productRequest);
    }

    @PutMapping(value = "/{applicationId}/accept")
    @Operation(summary = "Accept application", description = "Accepts the application with the provided ID")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void acceptApplication(@PathVariable Long applicationId) {
        applicationService.accept(applicationId);
    }

    @PutMapping(value = "/{applicationId}/reject")
    @Operation(summary = "Reject application", description = "Rejects the application with the provided ID")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void rejectApplication(@PathVariable Long applicationId, @RequestBody RejectionReasonRequest reasonRequest) {
        String rejectionReason = reasonRequest.getRejectionReason();
        applicationService.reject(applicationId, rejectionReason);
    }
}