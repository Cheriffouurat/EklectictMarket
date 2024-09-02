package com.example.eklecticproject.service;

import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.repository.IAbonnementRepositorie;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lowagie.text.Image;
import java.awt.*;
import com.lowagie.text.Rectangle;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

@Service
@AllArgsConstructor
public class PDFGeneratorServices {
    private  final IAbonnementRepositorie iAbonnementRepositorie;
    public void export(HttpServletResponse response, Integer idA) throws IOException {
        Abonnement abonnement = iAbonnementRepositorie.findById(idA).orElse(null);
        if (abonnement == null) {
            throw new IllegalArgumentException("Abonnement not found");
        }

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Ajouter le logo en haut à droite
        try {
            Image logo = Image.getInstance("src/main/resources/image/ec.png"); // Remplacez par le chemin complet vers votre image
            logo.setAbsolutePosition(450, 770); // Ajustez les coordonnées selon vos besoins
            logo.scaleAbsolute(100, 50); // Ajustez la taille de l'image
            PdfContentByte canvas = writer.getDirectContent();
            canvas.addImage(logo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.RED);

        // Ajouter un en-tête de page
        HeaderFooter head = new HeaderFooter(new Phrase(""), false);
        head.setBorder(Rectangle.NO_BORDER);
        document.setHeader(head);

        HeaderFooter footer = new HeaderFooter(new Phrase(""), true);
        footer.setBorder(Rectangle.NO_BORDER);
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.setFooter(footer);

        Paragraph header = new Paragraph("Abonnement", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Color.BLUE));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLUE);

        Paragraph idAbonnement = new Paragraph("Id D'abonnement: " + abonnement.getIdAbonnement(), infoFont);
        idAbonnement.setSpacingAfter(12f);
        document.add(idAbonnement);

        Paragraph telAbonnement = new Paragraph("Numéro de Téléphone Mobile d'Abonnement: " + abonnement.getTel(), infoFont);
        telAbonnement.setSpacingAfter(12f);
        document.add(telAbonnement);

        Paragraph dateAbonnement = new Paragraph("Date D'Abonnement: " + abonnement.getDateAbonnement(), infoFont);
        dateAbonnement.setSpacingAfter(12f);
        document.add(dateAbonnement);

        Paragraph dateExpiration = new Paragraph("Date D'expiration: " + abonnement.getDateExpiration(), infoFont);
        dateExpiration.setSpacingAfter(12f);
        document.add(dateExpiration);

        Paragraph dateDesabonnement = new Paragraph("Date de Desabonnement: " + abonnement.getDateDesabonnement(), infoFont);
        dateDesabonnement.setSpacingAfter(12f);
        document.add(dateDesabonnement);

        Paragraph idService = new Paragraph("Id de Service: " + abonnement.getIdService(), infoFont);
        idService.setSpacingAfter(12f);
        document.add(idService);

        Paragraph typeAbonnement = new Paragraph("Abonnement renouvelé tous les: " + abonnement.getType(), infoFont);
        typeAbonnement.setSpacingAfter(12f);
        document.add(typeAbonnement);

        // Ajouter le texte de remerciement
        Font thankYouFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.BLUE);
        Paragraph thankYou = new Paragraph("Merci pour votre confiance!", thankYouFont);
        thankYou.setSpacingBefore(20f); // Espacement au-dessus du texte
        thankYou.setAlignment(Element.ALIGN_CENTER);
        document.add(thankYou);
        try {
            Image cachet = Image.getInstance("src/main/resources/image/cachet.jpg");
            cachet.scaleToFit(100, 50);
            float x = (document.getPageSize().getWidth() - cachet.getScaledWidth()) / 2;
            float y = document.bottom() + 370; // Ajustez la position verticale selon vos besoins
            cachet.setAbsolutePosition(x, y);
            PdfContentByte canvas = writer.getDirectContent();
            canvas.addImage(cachet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.close();
    }
}

