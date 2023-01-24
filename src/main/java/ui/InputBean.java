package ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named("inputBean")
@SessionScoped
public class InputBean implements Serializable {
    @Getter
    @Setter
    private Double x;
    @Getter
    @Setter
    private Double y;
    @Getter
    @Setter
    private Double r = 1.0;

    public void show() {
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
    }
}