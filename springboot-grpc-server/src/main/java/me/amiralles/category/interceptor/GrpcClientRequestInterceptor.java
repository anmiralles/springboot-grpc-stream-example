package me.amiralles.category.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.config.MetadataInjector;

@Slf4j
public class GrpcClientRequestInterceptor implements ClientInterceptor {

  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      final MethodDescriptor<ReqT, RespT> methodDescriptor,
      final CallOptions callOptions,
      final Channel channel) {

    return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
        channel.newCall(methodDescriptor, callOptions)) {

      @Override
      public void start(Listener<RespT> responseListener, Metadata headers) {
        MetadataInjector injector = new MetadataInjector();
        headers.put(Metadata.Key.of("HOSTNAME", Metadata.ASCII_STRING_MARSHALLER), injector.getPodName());
        super.start(responseListener, headers);
      }
    };
  }
}
