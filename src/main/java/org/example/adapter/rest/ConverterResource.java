package org.example.adapter.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.example.application.usecase.ConvertToDollar;
import org.example.application.usecase.ConvertToWords;
import org.jboss.logging.Logger;

@Singleton
@Path("/v1/converter")
public class ConverterResource {
    private static final Logger LOG = Logger.getLogger(ConverterResource.class);

    @Inject
    ConvertToDollar convertToDollar;

    @Inject
    ConvertToWords convertToWords;

    @Path("/dollars")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public Response dollars(@Context Request request, @Context UriInfo uriInfo, Optional<DollarsRequest> body)
            throws Exception {
        LOG.infof("method=%s, path=%s, body=%s", request.getMethod(), uriInfo.getRequestUri(), body);

        if (!body.isPresent()) {
            LOG.error("request without body");

            return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
        }

        var message = body.get();
        var response = convertToDollar.execute(message.value);

        return Response.status(200).entity(response).build();
    }

    @Path("/words")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public Response words(@Context Request request, @Context UriInfo uriInfo, Optional<WordsRequest> body)
            throws Exception {
        LOG.infof("method=%s, path=%s, body=%s", request.getMethod(), uriInfo.getRequestUri(), body);

        if (!body.isPresent()) {
            LOG.error("request without body");

            return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
        }

        var message = body.get();
        var response = convertToWords.execute(message.value);

        return Response.status(200).entity(response).build();
    }
}

class DollarsRequest {
    public BigDecimal value;

    @Override
    public String toString() {
        return "DollarsRequest [value=" + value + "]";
    }
}

class WordsRequest {
    public BigInteger value;

    @Override
    public String toString() {
        return "WordsRequest [value=" + value + "]";
    }
}
