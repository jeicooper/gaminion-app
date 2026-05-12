package com.gaminion.summary;

import com.gaminion.session.Session;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Component
public class CardImageGenerator {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;

    public File generateCard(Session session, String outputPath) throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Background gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(15, 15, 30),
                WIDTH, HEIGHT, new Color(30, 20, 60)
        );
        g.setPaint(gradient);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Top accent bar
        g.setColor(new Color(99, 102, 241));
        g.fillRect(0, 0, WIDTH, 6);

        // Gaminion branding
        g.setColor(new Color(99, 102, 241));
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("GAMINION", 40, 45);

        // Game name
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 48));
        String gameName = session.getGame().getName();
        g.drawString(gameName, 40, 130);

        // Divider line
        g.setColor(new Color(99, 102, 241, 150));
        g.fillRect(40, 148, 720, 2);

        // Duration label
        g.setColor(new Color(160, 160, 200));
        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g.drawString("SESSION DURATION", 40, 195);

        // Duration value
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 56));
        String duration = formatDuration(session.getDurationSeconds());
        g.drawString(duration, 40, 260);

        // Date label
        g.setColor(new Color(160, 160, 200));
        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g.drawString("DATE PLAYED", 40, 310);

        // Date value
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        String date = session.getStartTime()
                .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy  •  hh:mm a"));
        g.drawString(date, 40, 340);

        // Notes section
        if (session.getNotes() != null && !session.getNotes().isEmpty()) {
            g.setColor(new Color(160, 160, 200));
            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.drawString("SESSION NOTES", 40, 385);

            g.setColor(new Color(200, 200, 220));
            g.setFont(new Font("SansSerif", Font.ITALIC, 16));
            String notes = session.getNotes();
            if (notes.length() > 80) notes = notes.substring(0, 80) + "...";
            g.drawString(notes, 40, 410);
        }

        // Bottom right watermark
        g.setColor(new Color(99, 102, 241, 100));
        g.setFont(new Font("SansSerif", Font.BOLD, 13));
        g.drawString("gaminion.app", WIDTH - 130, HEIGHT - 20);

        g.dispose();

        File outputDir = new File(outputPath);
        if (!outputDir.exists()) outputDir.mkdirs();

        String fileName = "session-" + session.getId() + "-" +
                System.currentTimeMillis() + ".png";
        File outputFile = new File(outputPath + "/" + fileName);
        ImageIO.write(image, "PNG", outputFile);

        return outputFile;
    }

    private String formatDuration(Long seconds) {
        if (seconds == null) return "0h 0m";
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        return hours + "h " + minutes + "m";
    }
}