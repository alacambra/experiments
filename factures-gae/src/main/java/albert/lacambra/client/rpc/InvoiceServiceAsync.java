package albert.lacambra.client.rpc;

import java.util.List;

import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.client.models.Invoice;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InvoiceServiceAsync {
	 void getInvoice(Long id, AsyncCallback<DTOInvoice> callback);
	 void getAllInvoices(AsyncCallback<List<DTOInvoice>> callback);
	 void addInvoice(DTOInvoice invoice, AsyncCallback<Long> callback);
}
