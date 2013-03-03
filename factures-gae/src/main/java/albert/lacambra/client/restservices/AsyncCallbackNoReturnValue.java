package albert.lacambra.client.restservices;

public interface AsyncCallbackNoReturnValue {

  void onFailure(ResponseException caught);
  void onSuccess();
}

