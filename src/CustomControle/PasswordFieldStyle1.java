package CustomControle;

import java.awt.*;
import javax.swing.*;

public class PasswordFieldStyle1 extends JPasswordField {
    public PasswordFieldStyle1() {
        super();
        
        // تخصيص الحقل
        setBackground(new Color(240, 240, 240)); // خلفية فاتحة
        setForeground(new Color(0, 0, 0)); // نص باللون الأسود
        setFont(new Font("Arial", Font.PLAIN, 16)); // خط بسيط
        
        setPreferredSize(new Dimension(200, 25));

        // إلغاء الحدود الافتراضية
        setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
    }

    public PasswordFieldStyle1(int width, int height) {
        super();
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // تحقق إذا كان النص فارغًا
        if (getPassword().length == 0) {
            g.setColor(new Color(169, 169, 169)); // لون النص عندما يكون فارغًا
            g.drawString("Enter password...", 5, 17); // رسم النص مع padding
        }
    }
}
