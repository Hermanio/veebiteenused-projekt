/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author toks
 */
public class RequestCacher {

    private static Map<String, Object> cachedResponses = new HashMap<>();

    public boolean isRequestRedundant(String requestId) {
        return cachedResponses.containsKey(requestId);
    }

    public Object getCachedResponse(String requestId) {
        return cachedResponses.get(requestId);
    }

    public void cacheResponse(String requestId, Object response) {
        cachedResponses.put(requestId, response);
    }

}
