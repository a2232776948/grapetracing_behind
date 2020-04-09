package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta implements Serializable {
    private Boolean keepAlive;

    private Boolean requireAuth;

    @Override
    public String toString() {
        return "Meta{" +
                "keepAlive=" + keepAlive +
                ", requireAuth=" + requireAuth +
                '}';
    }
}
