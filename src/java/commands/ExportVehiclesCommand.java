/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command creates listing of Vehicles and send it touser in PDF file .
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
import dao.IVehiclesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Vehicles;
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
public class ExportVehiclesCommand extends Command {

    private static Logger log = Logger.getLogger(ExportVehiclesCommand.class.getName());
    private static final float CELL_PADDING = 5;
    private static final float CELL_PADDING_HEADER = 10;
    private static final float SPACING = 10;
    private static final float[] COLUMNS_WIDTH = {1, 1, 2, 2, 2, 3, 2};
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.log(Level.INFO, "ExportVehiclesCommand instance: created");
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        List<Vehicles> listVehicles = vehiclesDAO.findAll();
        
        Document document = new Document(PageSize.A4, 50, 20, 15, 30);

        response.setContentType("application/pdf; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=VehiclesList.pdf");
        
        ServletOutputStream outStream = response.getOutputStream();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, outStream);
            document.open();
            Paragraph generalTitle= new Paragraph("General Vehicles List", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
            Chapter listChapter = new Chapter(generalTitle, 1);
            listChapter.setNumberDepth(0);
            Font fontDetails = FontFactory.getFont(FontFactory.HELVETICA, "UTF-8", true, 16, Font.BOLD, BaseColor.BLUE);
            //Paragraph tableTitle = new Paragraph("Vehicles details:", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new CMYKColor(0, 155, 100,17)));
            Paragraph tableTitle = new Paragraph("Vehicles details:", fontDetails);
            Section section1 = listChapter.addSection(tableTitle);         
            PdfPTable t = new PdfPTable(COLUMNS_WIDTH);
            t.setWidthPercentage(100);
            t.setSpacingBefore(SPACING);
            t.setSpacingAfter(SPACING);
//            t.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
//            t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            t.getDefaultCell().setPadding(CELL_PADDING);
            PdfPCell cellHeader1 = new PdfPCell(new Phrase("#"));
            cellHeader1.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader1.setPadding(CELL_PADDING_HEADER);
            //cellHeader1.setFixedHeight(CELL_PADDING_HEADER);
            t.addCell(cellHeader1);
            PdfPCell cellHeader2 = new PdfPCell(new Phrase("#Id"));
            cellHeader2.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader2.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader2);
            PdfPCell cellHeader3 = new PdfPCell(new Phrase("Mark"));
            cellHeader3.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader3.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader3);
            PdfPCell cellHeader4 = new PdfPCell(new Phrase("Model"));
            cellHeader4.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader4.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader4);
            PdfPCell cellHeader5 = new PdfPCell(new Phrase("Type"));
            cellHeader5.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader5.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader5);
            PdfPCell cellHeader6 = new PdfPCell(new Phrase("Assigned to Route"));
            cellHeader6.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader6.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader6);
            PdfPCell cellHeader7 = new PdfPCell(new Phrase("Status"));
            cellHeader7.setBackgroundColor(GrayColor.LIGHT_GRAY);
            cellHeader7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader7.setPadding(CELL_PADDING_HEADER);
            t.addCell(cellHeader7);
            
            Font fontCells = FontFactory.getFont(FontFactory.HELVETICA, "UTF-8", true, 12, Font.NORMAL, BaseColor.BLACK);
            IntStream vehPdfIndex = IntStream.rangeClosed(1, listVehicles.size());
            vehPdfIndex.forEach(idx -> {
                Vehicles currentVehicle = listVehicles.get(idx - 1);
                t.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.getDefaultCell().setPadding(CELL_PADDING);
                PdfPCell numOrder = new PdfPCell(new Phrase(String.valueOf(idx), fontCells));
                numOrder.setBackgroundColor(GrayColor.LIGHT_GRAY);
                numOrder.setHorizontalAlignment(Element.ALIGN_CENTER);
                t.addCell(numOrder);
                //t.addCell(new Phrase(String.valueOf(idx), fontCells));
                PdfPCell vehIdCell = new PdfPCell(new Phrase(currentVehicle.getVid().toString(), fontCells));
                vehIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //t.addCell(new Phrase(currentVehicle.getVid().toString(), fontCells));
                t.addCell(vehIdCell);
                t.addCell(new Phrase(currentVehicle.getVmark(), fontCells));
                t.addCell(new Phrase(currentVehicle.getVmodel(), fontCells));
                t.addCell(new Phrase(currentVehicle.getVehiclestypesname(), fontCells));
                t.addCell(new Phrase(currentVehicle.getAssignedtoname(), fontCells));
                t.addCell(new Phrase(currentVehicle.getStatus(), fontCells));
                //t.addCell(currentVehicle.getVehiclestypesdescription());
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
        
//      copyStreams(inStream, outStream);
//      inStream.close();
        outStream.close();
        
        //String page = ConfigManager.getInstance().getProperty(ConfigManager.VEHICLES_PAGE_PATH);
        String page = CommandHolder.getInstance().getCommandByName("cVehicles").execute(request, response);
        return page;
    }
    
//    private void copyStreams(FileInputStream in, ServletOutputStream out) throws IOException {
//        byte[] buffer = new byte[BUFFER_SIZE];
//        int read;
//        while ((read = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
//            out.write(buffer, 0, read);
//        }
//    }
    
}
