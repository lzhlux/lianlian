package cn.com.tichain.gateway.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myyml")
public class RouteWithRole {

    public List<Map<String, Object>> getRouteAndRole() {
        return routeAndRole;
    }

    public void setRouteAndRole(
            List<Map<String, Object>> routeAndRole) {
        this.routeAndRole = routeAndRole;
    }

    public List<Map<String, Object>> getAllowUrl() {
        return allowUrl;
    }

    public void setAllowUrl(
            List<Map<String, Object>> allowUrl) {
        this.allowUrl = allowUrl;
    }


    List<Map<String, Object>> routeAndRole = new ArrayList<>();
    List<Map<String, Object>> allowUrl = new ArrayList<>();


}
