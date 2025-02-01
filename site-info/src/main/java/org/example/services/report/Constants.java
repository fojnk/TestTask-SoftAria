package org.example.services.report;

public class Constants {
    public final static String BASE_TEMPLATE = """
            Здравствуйте, дорогая %s.
                        
            За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                        
            Исчезли следующие страницы: 
            %s
                        
            Появились следующие новые страницы: 
            %s
                        
            Изменились следующие страницы: 
            %s
                        
            С уважением, автоматизированная система мониторинга.
            """;

    public final static String DELIM = "\n";
}