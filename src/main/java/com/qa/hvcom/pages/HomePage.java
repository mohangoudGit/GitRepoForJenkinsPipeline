package com.qa.hvcom.pages;

import java.sql.Driver;


import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.io.Files;

import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import io.qameta.allure.Step;
import jakarta.mail.Session;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;



public class HomePage {
	
	
	private WebDriver driver;
	//Private By Loctaors
	private final By productsTab=By.xpath("//a[text()=' Products ']");
	private final By SolutionTab=By.xpath("//a[text()=' Solutions ']");
	private final By ServicesTab=By.xpath("//a[text()=' Services ']");
	private final By PartnersTab=By.xpath("//a[text()=' Partners ']");
	//private final By TechAlPart=By.xpath("//a[text()='Technology Alliance Partners']");
	private final By PartID=By.xpath("(//a[starts-with(@id,'global-systems-integrators')])[2]");
	
	private final By PartnerEchoSys=By.xpath("(//a[starts-with(@id,'our-partner-ecosystem')])[2]");
	private final By SupportPortalLink=By.xpath("//div[starts-with(@id,'customer-success-support')]/section/p/span[1]/a");
	private final By BlockStorageLink=By.xpath("//div[starts-with(@id,'storage-platforms')]/section/p/span[1]/a");
	String XLSXfilePath;		
	
	//div[starts-with(@id,'customer-success-support')]/section/p/span[1]/a  -- Support Portal
	
	//a[text()=' Solutions ']
	
		//Public Pgae Conctructor
	
	
	 //a[text()=' Products ']
	
	
		
		public HomePage(WebDriver driver) {
			
			this.driver=driver;
		}
		
		//public page actions/methods
		@Step("Geeting Product text")
		public String getProductText() {
			
			String Title =driver.getTitle();
			return driver.findElement(productsTab).getText();
			
			
		}
		//public page actions/methods
		
		public String goToProducts() throws InterruptedException {
			
			
			
			driver.findElement(productsTab).click();
			Thread.sleep(5000);
			
			String blockStorageProd=driver.findElement(BlockStorageLink).getText();
			return blockStorageProd;
			
	
		}
		
		public String goToSolutions() throws InterruptedException {
			
			String SolutionTabText= driver.findElement(SolutionTab).getText();
			
			driver.findElement(SolutionTab).click();
			
			return SolutionTabText;
			
	
		}
		
		public String goToServices() throws InterruptedException {
			
			String ServicesTabText= driver.findElement(ServicesTab).getText();
			System.out.println(ServicesTabText);
			
			driver.findElement(ServicesTab).click();
			
			Thread.sleep(5000);
			String Supportaltext=driver.findElement(SupportPortalLink).getText();
			
			return Supportaltext;
			
	
		}
		
public String goToPartners() throws InterruptedException {
			
			String ServicesTabText= driver.findElement(PartnersTab).getText();
			
			driver.findElement(PartnersTab).click();
			
			Thread.sleep(5000);
			
			String Text=driver.findElement(PartnerEchoSys).getText();
			//driver.findElement(PartnerEchoSys).click();
			
			//a[starts-with(@id, 'global-systems-integrators')]
			System.out.println("Text="+Text);
			
	
		//*****************************************************************
			
			
		//*******************************************************************	
			return Text;
		
		}



public  int getFrameIndexOfElement(WebDriver driver, By targetElementLocator) {
    // Step 1: Return focus to the main top-level page
    driver.switchTo().defaultContent();
    
    // Step 2: Find all top-level iframes currently on the page
    List<WebElement> frames = driver.findElements(By.tagName("iframe"));
    int totalFrames = frames.size();
    
    // Step 3: Iterate through each frame context
    for (int i = 0; i < totalFrames; i++) {
        try {
            // Switch focus into the current frame index
            driver.switchTo().frame(i);
            
            // Attempt to locate the target element inside this frame
            if (driver.findElements(targetElementLocator).size() > 0) {
                System.out.println("Success! Element found inside Frame Index: " + i);
                return i; // Target frame index found
            }
        } catch (Exception e) {
            // Handle occasional frame detach/loading errors gracefully
        } finally {
            // Always break back to the main document context before the next loop iteration
            driver.switchTo().defaultContent();
        }
    }
    
    System.out.println("Element was not found inside any top-level frame.");
    return -1; 
}

public  int SendReportInEmail() {
	
	
	  // 1. Define sender and recipient information
    final String senderEmail = "jenkins.frameworkdemo@gmail.com";
    final String appPassword = "czpjkthgdnfrwuah"; // Use an App Password, NOT your master password
    String recipientEmail = "jenkins.frameworkdemo@gmail.com";

    System.out.println(senderEmail);
    System.out.println(appPassword);
    System.out.println(recipientEmail);
    

    // 2. Set up SMTP server properties
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.ssl.enable","true");
    properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
    properties.put("mail.smtp.port", "465");           // TLS Port

    // 3. Create a mail session with authentication
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, appPassword);
        }
    });

    
    try {
        // 4. Create and compose the MimeMessage object
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Testing Java Mail API");
        message.setText("Hello! This email was sent successfully using Java code.");
        
        
        
        // 5. Transmit the email message
        Transport.send(message);
        System.out.println("Email sent successfully!");
        
        // added new code 
        
     // 4. Create the text body part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Please find the requested file attached to this email.");

        // 5. Create the attachment body part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        String filePath = "C:/Users/mgoud/MohanWorkData/Temp/Jira_dasboard.txt"; // Change to your local file path
        File file = new File(filePath);
        try {
			attachmentPart.attachFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Failed to send email due to an error: " + e.getMessage());
    }
    
	return 0;
	
	
	
}


@SuppressWarnings("static-access")
public  int SendReportInEmail2() throws InterruptedException {
	
	
	
	
	
	  // 1. Define sender and recipient information
    final String senderEmail = "jenkins.frameworkdemo@gmail.com";
    final String appPassword = "wxirehjhfyugpibr"; // Use an App Password, NOT your master password
    String recipientEmail = "jenkins.frameworkdemo@gmail.com";

    System.out.println(senderEmail);
    System.out.println(appPassword);
    System.out.println(recipientEmail);
    

    // 2. Set up SMTP server properties
    Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
   // props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.enable","true");
    props.put("mail.smtp.host","smtp.gmail.com"); // Replace with your SMTP server host
    props.put("mail.smtp.port", "465");           // TLS Port

	//+++++++++++++++++++++++++++++++++++++++++++
	
	

    // 2. Sender credentials
    final String username = "jenkins.frameworkdemo@gmail.com";
    // Note: For Gmail, use a 16-character 'App Password', NOT your actual account password
    final String password = "wxirehjhfyugpibr"; 

    // 3. Create the session with authentication
    Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        // 4. Create a default MimeMessage object
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jenkins.frameworkdemo@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jenkins.frameworkdemo@gmail.com"));
        message.setSubject("Project Report and Invoices");

        // 5. Create the text body part
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText("Hello, please find the requested documents attached to this email.");

        // 5. Create the text body part
        textBodyPart = new MimeBodyPart();
        textBodyPart.setText("Hello, please find the requested documents attached to this email.");

        // 6. Create the attachment body part
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
    //   String filePath = "C:/Users/mgoud/MohanWorkData/SeleniumWorkSpace/SeleniumFrameWorkDemo/target/surefire-reports/emailable-report.html";
      
       
       System.out.println("Wait Until Report Is Generated");
       Thread.sleep(60000);
       XLSXfilePath=System.getProperty("user.dir")+"\\target\\surefire-reports\\emailable-report.html";
          // Provide your local file path
        File file = new File(XLSXfilePath);
    //    C:\Users\mgoud\MohanWorkData\SeleniumWorkSpace\SeleniumFrameWorkDemo\SeleniumFrameWorkDemo\target\surefire-reports\emailable-report.html 

        System.out.println("FilePath="+XLSXfilePath);
     //   C:\Users\mgoud\MohanWorkData\SeleniumWorkSpace\SeleniumFrameWorkDemo\test-output 
     // Attach the file directly using the updated API
        attachmentBodyPart.attachFile(file);
        
        // 7. Combine both parts into a Multipart object
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        
        
     
		
        // Attach the file directly using the updated API
        attachmentBodyPart.attachFile(file);
        attachmentBodyPart.setDisposition(attachmentBodyPart.INLINE);
        
        //SeleniumFrameWorkDemo/target/chaintest/Index.html
        
        

        
        // 7. Combine both parts into a Multipart object
        multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        // 8. Set the multipart content as the email body
        message.setContent(multipart);

        // 9. Send the message
        System.out.println("Sending email with attachment...");
        Transport.send(message);
        System.out.println("Email sent successfully!");
        
        
        String workspacePath = "/var/lib/jenkins/workspace/My-Pipeline-Job";
        File workspace = new File(workspacePath);
        System.out.println(workspace);

    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }


	return 0;
	
	
	
}


public void refreshEclipeProject() throws CoreException, InterruptedException {
	
	
	IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("SeleniumFrameWorkDemo");

	if (project.exists() && project.isOpen()) {
	    // DEPTH_INFINITE crawls all sub-directories recursively
	    project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}
	Thread.sleep(10000);
}

}
