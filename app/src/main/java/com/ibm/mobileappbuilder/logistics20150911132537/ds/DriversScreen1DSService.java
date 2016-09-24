
package com.ibm.mobileappbuilder.logistics20150911132537.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "DriversScreen1DSService" REST Service implementation
 */
public class DriversScreen1DSService extends RestService<DriversScreen1DSServiceRest>{

    public static DriversScreen1DSService getInstance(){
          return new DriversScreen1DSService();
    }

    private DriversScreen1DSService() {
        super(DriversScreen1DSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "MnhnAOCr";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57e62c2ff222cb0300d486f6",
                path,
                "apikey=MnhnAOCr");
    }

}

