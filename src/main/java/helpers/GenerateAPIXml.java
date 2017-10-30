package helpers;

import cucumber.api.DataTable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GenerateAPIXml {

    public static String getLoginRequestBody(DataTable table) {
        List<List<String>> rawTable = table.raw();
        return  "<LoginRequest>\n" +
                "  <CommandType>" + rawTable.get(0).get(1) + "</CommandType>\n" +
                "  <UserId>" + rawTable.get(1).get(1) + "</UserId>\n" +
                "  <Password>" + rawTable.get(2).get(1) + "</Password>\n" +
                "</LoginRequest>";
    }

    private static String getVehicleSection(String manufacturer, String model, String year, String vin) {
        return "<csio:PCVEH>\n" +
                "<Manufacturer>" + manufacturer + "</Manufacturer>\n" +
                "<Model>" + model + "</Model>\n" +
                "<ModelYear>" + year + "</ModelYear>\n" +
                "<VehIdentificationNumber>" + vin + "</VehIdentificationNumber>\n" +
                "</csio:PCVEH>";
    }

    private static String getFileAttachmentSection(String title, String type, String filename) {
        return  "<FileAttachmentInfo>\n" +
                "<AttachmentDesc>" + title + "</AttachmentDesc>\n" +
                "<AttachmentTypeCd>csio:" + type + "</AttachmentTypeCd>\n" +
                "<AttachmentFilename>" + filename + "</AttachmentFilename>\n" +
                "</FileAttachmentInfo>";
    }

}
