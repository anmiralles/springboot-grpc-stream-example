package me.amiralles.category.grpc.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcClientResponseInterceptor implements ClientInterceptor {

  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      final MethodDescriptor<ReqT, RespT> methodDescriptor,
      final CallOptions callOptions,
      final Channel channel) {

    return new ForwardingClientCall.SimpleForwardingClientCall<>(
            channel.newCall(methodDescriptor, callOptions)) {

        @Override
        public void start(Listener<RespT> responseListener, Metadata headers) {
            super.start(
                    new ForwardingClientCallListener.SimpleForwardingClientCallListener<>(
                            responseListener) {

                        @Override
                        public void onMessage(RespT message) {
                            log.info("Intercepted response: {}", message);
                            super.onMessage(message);
                        }
                    }, headers);
        }
    };
  }
}
