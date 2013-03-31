package albert.lacambra.client.restservices.utils;


public interface AsyncCallbackNoReturnValue {

  void onFailure(ResponseException caught);
  void onSuccess();
}

