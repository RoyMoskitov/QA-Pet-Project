package api.Model;

import lombok.*;

@Data
@AllArgsConstructor
public class UserCreationRequest {
    private String name;
    private String job;
}
