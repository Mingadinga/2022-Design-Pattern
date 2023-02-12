package Article;

import java.util.ArrayList;

public class MainEntry {
    public static void main(String[] args) {
        String title = "디자인패턴";

        ArrayList<String> content = new ArrayList<>();
        content.add("첫째줄");
        content.add("둘째줄");
        content.add("셋째줄");
        content.add("째줄");

        String footer =  "2023.2.12, 민휘";

        Article article = new Article(title, content, footer);

        SimpleDisplayArticle style1 = new SimpleDisplayArticle(article);
        style1.display();

        CaptionDisplayArticle style2 = new CaptionDisplayArticle(article);
        style2.display();

    }
}
