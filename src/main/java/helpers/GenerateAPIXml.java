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

    public static String getCreateESlipRequestBody(DataTable table) {
        List<List<String>> rawTable = table.raw();
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<eSlipRq xmlns=\"http://www.ACORD.org/standards/PC_Surety/ACORD1/xml/\" xmlns:csio=\"http://www.CSIO.org/standards/PC_Surety/CSIO1/xml/\" xmlns:acme=\"http://www.ACME.org/standards/PC_Surety/ACME1/xml/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.CSIO.org/standards/PC_Surety/CSIO1/xml/ CSIO-v1-0-0-eSlip.xsd\">\n" +
                "<RqUID>c15e1a3f-dc80-495a-9507-c96178e1327e</RqUID>\n" +
                "<TransactionRequestDt>2013-09-30</TransactionRequestDt>\n" +
                "<Producer>\n" +
                "<ItemIdInfo>\n" +
                "<OtherIdentifier>\n" +
                "<OtherIdTypeCd>csio:CsioNetId</OtherIdTypeCd>\n" +
                "<OtherId>" + rawTable.get(0).get(1) + "</OtherId>\n" +
                "</OtherIdentifier>\n" +
                "</ItemIdInfo>\n" +
                "<GeneralPartyInfo>\n" +
                "<NameInfo>\n" +
                "<CommlName>\n" +
                "<CommercialName>" + rawTable.get(1).get(1) + "</CommercialName>\n" +
                "</CommlName>\n" +
                "</NameInfo>\n" +
                "<Addr>\n" +
                "<Addr1>321 Main Str.</Addr1>\n" +
                "<City>Cambridge</City>\n" +
                "<StateProvCd>ON</StateProvCd>\n" +
                "<PostalCode>L8Q 4O8</PostalCode>\n" +
                "</Addr>\n" +
                "</GeneralPartyInfo>\n" +
                "</Producer>\n" +
                "<InsuredOrPrincipal>\n" +
                "<GeneralPartyInfo>\n" +
                "<NameInfo>\n" +
                "<CommlName>\n" +
                "<CommercialName>Sibylle &amp; Rob Lingwood</CommercialName>\n" +
                "</CommlName>\n" +
                "</NameInfo>\n" +
                "<Addr>\n" +
                "<Addr1>3782 Easy Woods</Addr1>\n" +
                "<Addr2>Unit 4</Addr2>\n" +
                "<City>Prospect</City>\n" +
                "<StateProvCd>ON</StateProvCd>\n" +
                "<PostalCode>L8Q 4O8</PostalCode>\n" +
                "</Addr>\n" +
                "<Communications>\n" +
                "<PhoneInfo>\n" +
                "<PhoneNumber>+1-555-5551212</PhoneNumber>\n" +
                "</PhoneInfo>\n" +
                "<EmailInfo>\n" +
                "<EmailAddr>Sibylle@test.com</EmailAddr>\n" +
                "</EmailInfo>\n" +
                "<LanguageCd>EN</LanguageCd>\n" +
                "</Communications>\n" +
                "</GeneralPartyInfo>\n" +
                "</InsuredOrPrincipal>\n" +
                "<PartialPolicy>\n" +
                "<PolicyNumber>POL123461</PolicyNumber>\n" +
                "<LOBCd>csio:AUTO</LOBCd>\n" +
                "<ContractTerm>\n" +
                "<EffectiveDt>2017-07-15</EffectiveDt>\n" +
                "<ExpirationDt>2018-07-15</ExpirationDt>\n" +
                "</ContractTerm>\n" +
                "<csio:CompanyCd>XYZ</csio:CompanyCd>\n" +
                "<GeneralPartyInfo>\n" +
                "<NameInfo>\n" +
                "<CommlName>\n" +
                "<CommercialName>XYZ Insurance Company</CommercialName>\n" +
                "</CommlName>\n" +
                "</NameInfo>\n" +
                "<Addr>\n" +
                "<Addr1>123 Bay St.</Addr1>\n" +
                "<City>Toronto</City>\n" +
                "<StateProvCd>ON</StateProvCd>\n" +
                "<PostalCode>1a23b4</PostalCode>\n" +
                "</Addr>\n" +
                "</GeneralPartyInfo>\n" +
                "</PartialPolicy>\n" +
                "<BusinessPurposeTypeCd>csio:NBS</BusinessPurposeTypeCd>\n" +
                "<!-- 1st car -->\n" +
                "<csio:PCVEH>\n" +
                "<Manufacturer>Ford</Manufacturer>\n" +
                "<Model>Escape</Model>\n" +
                "<ModelYear>2017</ModelYear>\n" +
                "<VehIdentificationNumber>1FMCU9G92HUC02638</VehIdentificationNumber>\n" +
                "</csio:PCVEH>\n" +
                "<!-- 2nd car -->\n" +
                "<csio:PCVEH>\n" +
                "<Manufacturer>Audi</Manufacturer>\n" +
                "<Model>A8</Model>\n" +
                "<ModelYear>2017</ModelYear>\n" +
                "<VehIdentificationNumber>1FMCU9G92HUC02639</VehIdentificationNumber>\n" +
                "</csio:PCVEH>\n" +
                "<FileAttachmentInfo>\n" +
                "<AttachmentDesc>Policy Document</AttachmentDesc>\n" +
                "<AttachmentTypeCd>csio:DEC</AttachmentTypeCd>\n" +
                "<AttachmentFilename>DEC_POL123461.pdf</AttachmentFilename>\n" +
                "</FileAttachmentInfo>\n" +
                "<FileAttachmentInfo>\n" +
                "<AttachmentDesc>Billing Document</AttachmentDesc>\n" +
                "<AttachmentTypeCd>csio:BLN</AttachmentTypeCd>\n" +
                "<AttachmentFilename>BLN_POL123461.pdf</AttachmentFilename>\n" +
                "</FileAttachmentInfo>\n" +
                "<csio:RemarksInfo id=\"Salutation\">\n" +
                "<RemarkText>Dear Sibylle &amp; Rob Lingwood,</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "<csio:RemarksInfo id=\"Customer_Message\">\n" +
                "<RemarkText>Did you know...</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "<csio:RemarksInfo id=\"Carrier_Details\">\n" +
                "<RemarkText>\n" +
                "XYZ Insurance Company\n" +
                "Head Office:  Toronto, Ontario\n" +
                "Claims Service:  1-800-265-8600\t\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "<csio:RemarksInfo id=\"Broker_Details\">\n" +
                "<RemarkText>\n" +
                "ABC Brokerage\n" +
                "Contact:  Bob Smith\n" +
                "Telephone:  +1-514-123-1234\n" +
                "Email:  bob@abcbrokerage.com\n" +
                "Address:  321 Main Str., Cambridge, Ontario\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>\t\t\n" +
                "<csio:RemarksInfo id=\"Insured_Details\">\n" +
                "<RemarkText>\n" +
                "Sibylle Lingwood \n" +
                "Rob Lingwood\n" +
                "3782 Easy Woods \n" +
                "Unit 4  \n" +
                "Prospect, ON\n" +
                "L8Q 4O8\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>\t\n" +
                "</eSlipRq>";
    }

}
