package epamUniversity.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import epamUniversity.model.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 *
 * @author www.codejava.net
 */
public class TicketsPDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        Ticket ticket = (Ticket) model.get("ticket");

        doc.add(new Paragraph("Ticket"));

        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(PageSize.A4.getWidth()-25);
        table.setWidthPercentage(100.0f);

        table.setWidths(new float[]{3.0f, 2.0f, 2.0f, 2.0f,2.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Event", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Auditorium", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seat", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Owner", font));
        table.addCell(cell);

        // write table row data

        table.addCell(ticket.getEvent().getEventParent().getName());
        table.addCell(ticket.getEvent().getAuditorium().getName());
        table.addCell(String.valueOf(ticket.getSeat()));
        table.addCell(String.valueOf(ticket.getPrice()));
        table.addCell(ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName());


        doc.add(table);
    }

}