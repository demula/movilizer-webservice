package com.movilizer.mds.webservice.services;

import com.movilitas.movilizer.v12.MovilizerRequest;
import com.movilitas.movilizer.v12.MovilizerResponse;
import com.movilitas.movilizer.v12.MovilizerWebServiceV12;
import com.movilitas.movilizer.v12.MovilizerWebServiceV12Service;
import com.movilizer.mds.webservice.defaults.DefaultValues;
import com.movilizer.mds.webservice.models.FutureCallback;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnit4.class)
public class MovilizerWebServiceTest {
    private static long SYSTEM_ID = 1L; //Put your own here
    private static String PASSWORD = "pass"; //Put your own here

    private MovilizerWebService webService;

    @Before
    public void setUp() throws Exception {
        MovilizerWebServiceV12 movilizerCloud = new MovilizerWebServiceV12Service().getMovilizerWebServiceV12Soap11();
        webService = new MovilizerWebService(movilizerCloud);
        webService.setEndpoint(DefaultValues.MOVILIZER_ENDPOINT.getMdsUrl());
    }

    @Ignore
    @Test
    public void testPerformEmptyRequest() throws Exception {
        MovilizerRequest request = new MovilizerRequest();
        request = webService.prepareUploadRequest(SYSTEM_ID, PASSWORD, request);
        MovilizerResponse response = webService.getReplyFromCloudSync(request);
        assertThat(response, is(notNullValue()));
        assertThat(webService.responseHasErrors(response), is(false));
    }

    @Ignore
    @Test
    public void testPerformEmptyRequestAsync() throws Exception {
        MovilizerRequest request = new MovilizerRequest();
        request = webService.prepareUploadRequest(SYSTEM_ID, PASSWORD, request);
        webService.getReplyFromCloud(request, new FutureCallback<MovilizerResponse>() {
            @Override
            public void onSuccess(MovilizerResponse movilizerResponse) {

            }

            @Override
            public void onComplete(MovilizerResponse movilizerResponse, Exception e) {
                assertThat(movilizerResponse, is(notNullValue()));
                assertThat(webService.responseHasErrors(movilizerResponse), is(false));
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
