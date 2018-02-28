package helpers;

import cucumber.api.DataTable;

import java.util.List;

import static helpers.Procedures.*;

public class GenerateAPIXml {

    public static String getLoginRequestBody(DataTable table) {
        List<List<String>> rawTable = table.raw();
        return  "<LoginRequest>\n" +
                "  <CommandType>" + rawTable.get(0).get(1) + "</CommandType>\n" +
                "  <UserId>" + rawTable.get(1).get(1) + "</UserId>\n" +
                "  <Password>" + rawTable.get(2).get(1) + "</Password>\n" +
                "</LoginRequest>";
    }

    public static String getCsioNETLoginRequestBody(DataTable table) {
        List<List<String>> rawTable = table.raw();
        return  "<Request>\n" +
                "  <CommandType>" + rawTable.get(0).get(1) + "</CommandType>\n" +
                "  <CSIOnetID>" + rawTable.get(1).get(1) + "</CSIOnetID>\n" +
                "  <CSIOnetPassword>" + rawTable.get(2).get(1) + "</CSIOnetPassword>\n" +
                "</Request>\n";
    }

    public static String getCsioNETListOfMessagesBody(String sessionGUID, String userGUID, DataTable table) {
        List<List<String>> rawTable = table.raw();
        return  "<Request>\n" +
                "\t<CommandType>List</CommandType>\n" +
                "\t<SessionGUID>" + sessionGUID + "</SessionGUID>\n" +
                "\t<UserGUID>" + userGUID + "</UserGUID>\n" +
                "\t<FromDateTime>" + rawTable.get(0).get(1) + "</FromDateTime>\n" +
                "\t<ToDateTime>" + rawTable.get(1).get(1) + "</ToDateTime>\n" +
                "\t<Page>" + rawTable.get(2).get(1) + "</Page>\n" +
                "\t<PageSize>" + rawTable.get(3).get(1) + "</PageSize>\n" +
                "</Request>";
    }

    public static String getCsioNETSingleMessagesBody(String sessionGUID, String userGUID, String messageGUID) {
        return  "<Request>\n" +
                "    <CommandType>Retrieve</CommandType>\n" +
                "    <SessionGUID>" + sessionGUID + "</SessionGUID>\n" +
                "    <UserGUID>" + userGUID + "</UserGUID>\n" +
                "    <MessageGUID>" + messageGUID + "</MessageGUID>\n" +
                "    <Delete>0</Delete>\n" +
                "</Request>";
    }

    public static String getCsioNETLogoutRequestBody(String sessionID) {
        return  "<Request>\n" +
                "  <CommandType>SignOut</CommandType>\n" +
                "  <SessionGUID>" + sessionID + "</SessionGUID>\n" +
                "</Request>";
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
                "<TransactionSeqNumber>1</TransactionSeqNumber>\n" +
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
                "<EmailAddr>" + rawTable.get(2).get(1) + "</EmailAddr>\n" +
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
                "<AttachmentContent>" + Procedures.convertFileToBase64() + "</AttachmentContent>\n" +
                "</FileAttachmentInfo>\n" +
                "<csio:RemarksInfo>\n" +
                "<ItemIdInfo>\n" +
                "<csio:FixedId>Salutation</csio:FixedId>\n" +
                "</ItemIdInfo>\n" +
                "<RemarkText>Dear Test User,</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "\n" +
                "<csio:RemarksInfo>\n" +
                "<ItemIdInfo>\n" +
                "<csio:FixedId>Customer_Message</csio:FixedId>\n" +
                "</ItemIdInfo>\n" +
                "<RemarkText>Did you know...</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "\n" +
                "<csio:RemarksInfo>\n" +
                "<ItemIdInfo>\n" +
                "<csio:FixedId>Carrier_Details</csio:FixedId>\n" +
                "</ItemIdInfo>\n" +
                "<RemarkText>\n" +
                "XYZ Insurance Company\n" +
                "Head Office:  Toronto, Ontario\n" +
                "Claims Service:  1-800-265-8600\t\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>\n" +
                "\n" +
                "<csio:RemarksInfo>\n" +
                "<ItemIdInfo>\n" +
                "<csio:FixedId>Broker_Details</csio:FixedId>\n" +
                "</ItemIdInfo>\n" +
                "<RemarkText>\n" +
                "Test Broker\n" +
                "Contact:  Bob Smith\n" +
                "Telephone:  +1-514-123-1234\n" +
                "Email:  bob@abcbrokerage.com\n" +
                "Address:  321 Main Str., Cambridge, Ontario\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>    \n" +
                "\n" +
                "<csio:RemarksInfo>\n" +
                "<ItemIdInfo>\n" +
                "<csio:FixedId>Insured_Details</csio:FixedId>\n" +
                "</ItemIdInfo>\n" +
                "<RemarkText>\n" +
                "Test User \n" +
                "\n" +
                "3782 Easy Woods \n" +
                "Unit 4  \n" +
                "Prospect, ON\n" +
                "L8Q 4O8\n" +
                "</RemarkText>\n" +
                "</csio:RemarksInfo>  " +
                "</eSlipRq>";
    }

    public static String getCsioNetSendMessageBody(String sessionGUID, String userGUID, DataTable table) {
        List<List<String>> rawTable = table.raw();

        String xmlAttachment = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
                "<CommonSvcRs xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:csio=\"http://www.CSIO.org/standards/PC_Surety/CSIO1/xml/\" xmlns:acme=\"http://www.ACME.org/standards/PC_Surety/ACME1/xml/\" xsi:schemaLocation=\"http://www.ACORD.org/standards/PC_Surety/ACORD1/xml/ACORD-ca-v1-23-0-yescode.xsd\" xmlns=\"http://www.ACORD.org/standards/PC_Surety/ACORD1/xml/\">\n" +
                "  <RqUID>99c520f0-f10a-49f4-8392-019960ded650</RqUID>\n" +
                "  <SPName>myproofofinsurance.ca</SPName>\n" +
                "  <ActivityNoteRs>\n" +
                "    <RqUID>90354320-444a-41da-83d3-fe8ba1ec5b62</RqUID>\n" +
                "    <TransactionResponseDt>" + Procedures.generateDateWithDashes() + "T00:00:00Z</TransactionResponseDt>\n" +
                "    <MsgStatus>\n" +
                "      <MsgStatusCd>ResultPendingOutOfBand</MsgStatusCd>\n" +
                "    </MsgStatus>\n" +
                "    <TransactionSeqNumber>1</TransactionSeqNumber>\n" +
                "    <InsuredOrPrincipal>\n" +
                "      <GeneralPartyInfo>\n" +
                "        <NameInfo>\n" +
                "          <CommlName>\n" +
                "            <CommercialName>Bartlomiej Sternalski</CommercialName>\n" +
                "          </CommlName>\n" +
                "        </NameInfo>\n" +
                "      </GeneralPartyInfo>\n" +
                "    </InsuredOrPrincipal>\n" +
                "    <PartialPolicy>\n" +
                "      <PolicyNumber>pol1233456</PolicyNumber>\n" +
                "      <LOBCd>csio:AUTO</LOBCd>\n" +
                "      <ContractTerm>\n" +
                "        <EffectiveDt>" + rawTable.get(1).get(1) + "</EffectiveDt>\n" +
                "        <ExpirationDt>" + rawTable.get(2).get(1) + "</ExpirationDt>\n" +
                "      </ContractTerm>\n" +
                "      <csio:CompanyCd>ROY</csio:CompanyCd>\n" +
                "    </PartialPolicy>\n" +
                "    <BusinessPurposeTypeCd>csio:MEM</BusinessPurposeTypeCd>\n" +
                "    <FileAttachmentInfo>\n" +
                "      <AttachmentDesc>OK - eDelivery - Bartlomiej Sternalski - " + rawTable.get(0).get(1) + "</AttachmentDesc>\n" +
                "      <AttachmentTypeCd>csio:OTH</AttachmentTypeCd>\n" +
                "      <MIMEContentTypeCd>application/octet-stream</MIMEContentTypeCd>\n" +
                "      <MIMEEncodingTypeCd>BASE64</MIMEEncodingTypeCd>\n" +
                "      <AttachmentFilename>Bartlomiej_Sternalski_" + rawTable.get(0).get(1) + "_MEM.txt</AttachmentFilename>\n" +
                "      <AttachmentStatusCd>csio:5</AttachmentStatusCd>\n" +
                "    </FileAttachmentInfo>\n" +
                "  </ActivityNoteRs>\n" +
                "</CommonSvcRs>";

        String txtAttachment = "OK - eDelivery - Bartlomiej Sternalski - " + rawTable.get(0).get(1) + "\n" +
                "\n" +
                "\n" +
                "Client information:\n" +
                "Name: Bartlomiej Sternalski\n" +
                "Email: " + rawTable.get(3).get(1) + "\n" +
                "\n" +
                "Policy information:\n" +
                "Policy Number: " + rawTable.get(0).get(1) + "\n" +
                "Line of business: csio:AUTO\n" +
                "Effective date: " + rawTable.get(1).get(1) + "\n" +
                "Expiry date: " + rawTable.get(2).get(1) + "\n" +
                "\n" +
                "Vehicle Information:\n" +
                "Make: AlfaRomeo\n" +
                "Model: Mito\n" +
                "Year: 2017\n" +
                "VIN: ALFA939383838\n" +
                "\n" +
                "Document Information:\n" +
                "Type: csio:OTH\n" +
                "Description: OK - eDelivery - Bartlomiej Sternalski - " + rawTable.get(0).get(1) + "\n" +
                "File name: Bartlomiej_Sternalski_" + rawTable.get(0).get(1) + "_MEM.txt\n";

        return "<Request>\n" +
                "  <CommandType>Send</CommandType>\n" +
                "  <SessionGUID>" + sessionGUID + "</SessionGUID>\n" +
                "  <UserGUID>" + userGUID + "</UserGUID>\n" +
                "  <ToEmailAddress>avanade@vendor.edi.csio.com</ToEmailAddress>\n" +
                "  <FromEmailAddress>avanade@vendor.edi.csio.com</FromEmailAddress>\n" +
                "  <MessageType>eDoc</MessageType>\n" +
                "  <MessageSubject>XML Xmit Msg-Id#:" + Procedures.generateDateWithCurrentMiliseconds() + "avanade@vendor.edi.csio.com</MessageSubject>\n" +
                "  <Attachments count=\"2\">\n" +
                "     <Attachment filename=\"Bartlomiej_Sternalski_" + rawTable.get(0).get(1) + "_MEM.xml\" mimetype=\"application/xml\">\n" +
                "\n" +
                Procedures.convertStringToBase64(xmlAttachment) +
                "\n" +
                "\n" +
                "</Attachment>\n" +
                "        <Attachment filename=\"Bartlomiej_Sternalski_" + rawTable.get(0).get(1) + "_MEM.txt\" mimetype=\"application/octet-stream\">\n" +
                "\n" +
                Procedures.convertStringToBase64(txtAttachment) +
                "\n" +
                "</Attachment>\n" +
                "  </Attachments>\n" +
                "</Request>\n";
    }

}
