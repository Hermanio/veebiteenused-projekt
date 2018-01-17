/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toks
 */
public class AuthorizationService {

    private List<String> allowedApiTokens;
    private Map<String, String> apiTokenOwnerPairs;

    public AuthorizationService() {
        allowedApiTokens = new ArrayList<>();
        allowedApiTokens.add("59584e686332526863325268633252");
        allowedApiTokens.add("616466617364666173646661736432");
        allowedApiTokens.add("616467686a6a6a6a6a6a6a6a6a6a6a");

        apiTokenOwnerPairs = new HashMap<>();
        apiTokenOwnerPairs.put("59584e686332526863325268633252", "Jonathan Wickers");
        apiTokenOwnerPairs.put("616466617364666173646661736432", "The Archive Team");
        apiTokenOwnerPairs.put("616467686a6a6a6a6a6a6a6a6a6a6a", "Archives R Us");

    }

    public boolean isAuthorized(String apiToken) {
        return allowedApiTokens.contains(apiToken);
    }

    public String getSubmitterFromApiToken(String apiToken) {
        return apiTokenOwnerPairs.get(apiToken);
    }
}
