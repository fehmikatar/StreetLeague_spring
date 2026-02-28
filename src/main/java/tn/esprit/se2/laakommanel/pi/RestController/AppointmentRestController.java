package tn.esprit.se2.laakommanel.pi.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.se2.laakommanel.pi.entites.Appointment;
import tn.esprit.se2.laakommanel.pi.entites.User;
import tn.esprit.se2.laakommanel.pi.dto.AppointmentRequest;
import tn.esprit.se2.laakommanel.pi.dto.AppointmentResponse;
import tn.esprit.se2.laakommanel.pi.services.IAppointmentService;
import tn.esprit.se2.laakommanel.pi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "📅 Appointment management operations")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final IAppointmentService appointmentService;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppointmentResponse> responses = appointments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        AppointmentResponse response = convertToResponse(appointment);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = convertToEntity(request);
        Appointment created = appointmentService.createAppointment(appointment);
        AppointmentResponse response = convertToResponse(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest request) {
        Appointment appointment = convertToEntity(request);
        Appointment updated = appointmentService.updateAppointment(id, appointment);
        AppointmentResponse response = convertToResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        List<AppointmentResponse> responses = appointments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByUser(@PathVariable Long userId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUser(userId);
        List<AppointmentResponse> responses = appointments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =============== Méthodes de conversion ===============

    private Appointment convertToEntity(AppointmentRequest request) {
        if (request == null) return null;

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setReason(request.getReason());
        appointment.setStatus(request.getStatus());
        appointment.setNotes(request.getNotes());

        return appointment;
    }

    private AppointmentResponse convertToResponse(Appointment appointment) {
        if (appointment == null) return null;

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .user(appointment.getUser())
                .doctor(appointment.getDoctor())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .notes(appointment.getNotes())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }
}