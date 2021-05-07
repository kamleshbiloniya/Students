package com.helloswf;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import lombok.Data;

import javax.security.auth.login.AppConfigurationEntry;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class StudentConfiguration extends Configuration {
    public static final String APP_NAME = "StudentApp";

    @Valid
    @NotNull
    public JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

}
