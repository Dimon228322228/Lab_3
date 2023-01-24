package clock;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@SessionScoped
@Named("timeZoneBean")
public class TimeZoneBean implements Serializable {
    @Getter
    private String timezone = "Europe/Moscow";

    public void updateTimezone() {
        timezone = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone");
    }
}