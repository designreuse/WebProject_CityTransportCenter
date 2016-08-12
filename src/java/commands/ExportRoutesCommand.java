/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command creates listing of Marshroutes and send it to user in PDF file .
 * 
 */
package commands;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.CommandHolder;
import dao.IRoutesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */
public class ExportRoutesCommand extends Command {

    private static Logger log = Logger.getLogger(ExportRoutesCommand.class.getName());
    private static final float CELL_PADDING = 5;
    private static final float CELL_PADDING_HEADER = 10;
    private static final float SPACING = 10;
    private static final float[] COLUMNS_WIDTH = {1, 1, 2, 7};
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.log(Level.INFO, "ExportRoutesCommand instance: created");
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        List<Marshroutes> listRoutes = routesDAO.findAll();
        
        Document document = new Document(PageSize.A4, 50, 20, 15, 30);

        response.setContentType("application/pdf; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=MarshroutesList.pdf");
        
        ServletOutputStream outStream = response.getOutputStream();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, outStream);
            document.open();
            Paragraph generalTitle= new Paragraph("General Marshroutes List", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
            Chapter listChapter = new Chapter(generalTitle, 1);
            listChapter.setNumberDepth(0);
            Font fontDetails = FontFactory.getFont(FontFactory.HELVETICA, "UTF-8", true, 16, Font.BOLD, BaseColor.BLUE);
            Paragraph tableTitle = new Paragraph("Marshroutes details:", fontDetails);
            Section section1 = listChapter.addSection(tableTitle);         
            PdfPTable t = new PdfPTable(COLUMNS_WIDTH);
            t.setWidthPercentage(100);
            t.setSpacingBefore(SPACING);
            t.setSpacingAfter(SPACING);
            PdfPCell cellHeader1 = new PdfPCell(new Phrase("#"));
            cellHeader1.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader1.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader1);
            PdfPCell cellHeader2 = new PdfPCell(new Phrase("#Id"));
            cellHeader2.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader2.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader2);
            PdfPCell cellHeader3 = new PdfPCell(new Phrase("Marshroute Name"));
            cellHeader3.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader3.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader3);
            PdfPCell cellHeader4 = new PdfPCell(new Phrase("Marshroute Description"));
            cellHeader4.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader4.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader4);
            
            Font fontCells = FontFactory.getFont(FontFactory.HELVETICA, "UTF-8", true, 12, Font.NORMAL, BaseColor.BLACK);
            IntStream vehPdfIndex = IntStream.rangeClosed(1, listRoutes.size());
            vehPdfIndex.forEach(idx -> {
                Marshroutes currentRoute = listRoutes.get(idx - 1);
                t.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.getDefaultCell().setPadding(CELL_PADDING);
                PdfPCell numOrder = new PdfPCell(new Phrase(String.valueOf(idx), fontCells));
                numOrder.setBackgroundColor(GrayColor.LIGHT_GRAY);
                numOrder.setHorizontalAlignment(Element.ALIGN_CENTER);
                t.addCell(numOrder);
                PdfPCell vehIdCell = new PdfPCell(new Phrase(currentRoute.getIdmarshroutes().toString(), fontCells));
                vehIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                t.addCell(vehIdCell);
                t.addCell(new Phrase(currentRoute.getRoutename(), fontCells));
                t.addCell(new Phrase(currentRoute.getDescription(), fontCells));
            });
            section1.add(t);        
            document.add(listChapter);
//            document.close();
//            writer.close();
            
        } catch (DocumentException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            document.close();
            writer.close();
        }

        outStream.close();
        
        //String page = ConfigManager.getInstance().getProperty(ConfigManager.VEHICLES_PAGE_PATH);
        String page = CommandHolder.getInstance().getCommandByName("cRoutes").execute(request, response);
        return page;
    }
}
