package api.Model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationResponse {
    private String name;
    private String job;
    private String id;
    private LocalDateTime createdAt;
}
