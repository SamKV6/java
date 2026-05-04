import java.util.Iterator;

/**
 * Вариант 1: Класс Предложение с итератором по словам
 */
public class Variant1_Sentence {

    // ─────────────────────────────────────────────────────────────
    //  Класс Sentence
    // ─────────────────────────────────────────────────────────────
    static class Sentence implements Iterable<String> {

        private final String[] words;

        public Sentence(String text) {
            // Разбиваем по пробелам (один и более), убираем пунктуацию по краям слов
            if (text == null || text.isBlank()) {
                this.words = new String[0];
            } else {
                this.words = text.trim().split("\\s+");
            }
        }

        @Override
        public Iterator<String> iterator() {
            return new WordIterator();
        }

        // ── Внутренний класс-итератор ──────────────────────────
        private class WordIterator implements Iterator<String> {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException(
                            "Слова закончились (индекс " + index + ")");
                }
                return words[index++];
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Демонстрация
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        String text = "Итераторы в Java очень удобны и полезны";
        Sentence sentence = new Sentence(text);

        System.out.println("=== Вариант 1: Итератор по словам ===");
        System.out.println("Предложение: \"" + text + "\"");
        System.out.println();

        // Способ 1: цикл for-each (использует Iterable)
        System.out.println("▶ for-each:");
        for (String word : sentence) {
            System.out.println("  слово → " + word);
        }

        System.out.println();

        // Способ 2: явное использование итератора
        System.out.println("▶ Явный итератор:");
        Iterator<String> it = sentence.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.printf("  [%d] %s%n", i++, it.next());
        }

        System.out.println();

        // Способ 3: пустое предложение
        Sentence empty = new Sentence("   ");
        System.out.println("▶ Пустое предложение (hasNext сразу false): "
                + empty.iterator().hasNext());
    }
}
