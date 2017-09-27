package helpers;

import net.itarray.automotion.tools.helpers.MailService;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.Multipart;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailExtractor {

    public void getPasswordsFromEmails() throws Exception {
        MailService mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "bartavanade@gmail.com", "Infusi0n!");
        mailService.setFolder(MailService.MailFolder.INBOX);
        System.out.println("No of messages in INBOX folder: " + mailService.getMessageCount());
        Message[] messages = mailService.getMessages();
        for (Message temp : messages) {
            try {
                Multipart multipart = (Multipart) temp.getContent();
                for (int j = 0; j < multipart.getCount(); j++) {
                    BodyPart bodyPart = multipart.getBodyPart(j);
                    if (bodyPart.isMimeType("text/plain")) {
                        System.out.println("Body: " + bodyPart.getContent());
                    }
                }
            } catch (Exception e) {
                String body = temp.getContent().toString();
                Pattern pattern = Pattern.compile("Password: .{8}");
                Matcher matcher = pattern.matcher(body);
                if (matcher.find()) {
                    System.out.println("Extracted password: " + matcher.group(0).substring(10));
                } else {
                    System.out.println("STRING NOT FOUND");
                }
            }
        }
    }

    public static String getPasswordFromLastEmail() throws Exception {
        boolean successFlag = false;
        MailService mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "bartavanade@gmail.com", "Infusi0n!");
        mailService.setFolder(MailService.MailFolder.INBOX);
        String body = "";
        for (int i = 0; i<6; i++) {
            System.out.println("INFO: Checking email...");
            Thread.sleep(10000);
            if (mailService.getMessageCount() > 0) {
                Message message = mailService.getLastMesage();
                try {
                    body = message.getContent().toString();
                } catch (Exception e) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        BodyPart bodyPart = multipart.getBodyPart(j);
                        if (bodyPart.isMimeType("text/plain")) {
                            System.out.println("Body: " + bodyPart.getContent());
                        }
                    }
                }
                successFlag = true;
                break;
            }
        }
        mailService.logout();
        if (successFlag) {
            Pattern pattern = Pattern.compile("(?<=Password: ).{8}");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return matcher.group(0);
            } else {
                return body;
            }
        } else {
            System.out.println("INFO: User didn't receive email after 60 sec");
        }
        return "";
    }

    public static String getLastEmailTitle() throws Exception {
        boolean successFlag = false;
        MailService mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "bartavanade@gmail.com", "Infusi0n!");
        mailService.setFolder(MailService.MailFolder.INBOX);
        String emailTitle = "";
        for (int i = 0; i<6; i++) {
            System.out.println("INFO: Checking email...");
            Thread.sleep(10000);
            if (mailService.getMessageCount() > 0) {
                Message message = mailService.getLastMesage();
                emailTitle = message.getSubject();
                successFlag = true;
                break;
            }
        }
        mailService.logout();
        if (!successFlag) System.out.println("INFO: User didn't receive email after 60 sec");
        return emailTitle;
    }

    public static void deleteMessages() throws Exception {
        MailService mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "bartavanade@gmail.com", "Infusi0n!");
        mailService.setFolder(MailService.MailFolder.INBOX);
        Message[] messages = mailService.getMessages();
        for (Message temp : messages) {
            temp.setFlag(Flags.Flag.DELETED, true);
        }
        mailService.logout();
    }

    public static int getNoOfMessages() throws Exception {
        MailService mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "bartavanade@gmail.com", "Infusi0n!");
        mailService.setFolder(MailService.MailFolder.INBOX);
        Message[] messages = mailService.getMessages();
        mailService.logout();
        return messages.length;
    }
}
