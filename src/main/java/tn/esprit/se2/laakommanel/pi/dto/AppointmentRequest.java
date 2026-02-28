package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.AppointmentStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private String reason;
    private AppointmentStatus status;
    private String notes;
}