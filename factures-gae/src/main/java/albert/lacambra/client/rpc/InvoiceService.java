package albert.lacambra.client.rpc;

import java.util.List;

import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.client.models.Invoice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("invoice")
public interface InvoiceService extends RemoteService{
	
	public DTOInvoice getInvoice(Long id);
	public Long addInvoice(DTOInvoice i);
	public List<DTOInvoice> getAllInvoices();
}
