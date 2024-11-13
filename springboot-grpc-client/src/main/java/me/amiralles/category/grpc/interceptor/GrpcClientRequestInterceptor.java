package me.amiralles.category.grpc.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

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
        log.info("Intercepted request: {}", responseListener);
        super.start(responseListener, headers);
      }
    };
  }
}
