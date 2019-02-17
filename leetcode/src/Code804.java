import java.util.HashSet;
import java.util.Set;

/**
 * 唯一摩尔斯密码词
 *
 * @author leon
 * @since 2019-01-28
 */
public class Code804 {
    String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(getMorse(word.charAt(i)));
            }
            set.add(stringBuilder.toString());
        }
        return set.size();
    }

    private String getMorse(char c) {
        return codes[c - 'a'];
    }
}
