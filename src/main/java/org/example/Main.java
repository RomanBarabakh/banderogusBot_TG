package org.example;

import org.checkerframework.checker.signature.qual.ClassGetName;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main extends TelegramLongPollingBot {

    private Map<Long, Integer> levels = new HashMap<>();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }


    @Override
    public String getBotUsername() {
        return "BaanderoogusBot";
    }

    @Override
    public String getBotToken() {
        return "6048786627:AAGzihDI21nmbo-VQWjG0h-13Doq5D34jhU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {

            //send image level 1
            sendImage("level-1", chatId);

            SendMessage message = createMessage("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
                    "\n" +
                    "Ти отримуєш гусака №71\n" +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔️виконувати завдання\n" +
                    "✔️переходити на наступні рівні\n" +
                    "✔️заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень");
            message.setChatId(chatId);


            List<String> buttons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет) ",
                    "Зібрати кошти патріотичними піснями (+15 монет) ",
                    "Вступити в Міністерство Мемів України (+15 монет) ",
                    "Запустити волонтерську акцію (+15 монет) ",
                    "Вступити до лав тероборони (+15 монет) "
            );

            buttons = getRandom3(buttons);


            attachButtons(message, Map.of(
                    buttons.get(0), "level_1_task",
                    buttons.get(1), "level_1_task",
                    buttons.get(2), "level_1_task"
            ));

            sendApiMethodAsync(message);
        }

        if(update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("level_1_task") && getLevel(chatId) == 1) {
                //increase level
                setLevel(chatId, 2);

                //send image
                sendImage("level-2", chatId);


                //send messege
                SendMessage message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет) ",
                        "Пройти курс молодого бійця (+15 монет) ",
                        "Задонатити на ЗСУ (+15 монет) ",
                        "Збити дрона банкою огірків (+15 монет) ",
                        "Зробити запаси коктейлів Молотова (+15 монет) "
                );

                attachButtons(message, Map.of(
                        buttons.get(0), "level-2_task",
                        buttons.get(1), "level_2_task",
                        buttons.get(2), "level_2_task"
                ));
                sendApiMethodAsync(message);
            }

            //send image level 3
            if (update.getCallbackQuery().getData().equals("level_2_task") && getLevel(chatId) == 2) {
                //increase level
                setLevel(chatId,3);

                //send image

                sendImage("level-3", chatId);

                //send messege

                SendMessage message = createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");

                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет) ",
                        "Відвезти гуманітарку на передок (+15 монет) ",
                        "Знайти зрадника та здати в СБУ (+15 монет) " ,
                        "Навести арту на орків (+15 монет) ",
                        "Притягнути танк трактором (+15 монет) "
                );

                attachButtons(message, Map.of(
                        buttons.get(0), "level-3_task",
                        buttons.get(1), "level_3_task",
                        buttons.get(2), "level_3_task"
                ));
                sendApiMethodAsync(message);
            }
            //send image level 4
            if (update.getCallbackQuery().getData().equals("level_3_task") && getLevel(chatId) == 3) {
                //increase level
                setLevel(chatId,4);

                //send image

                sendImage("level-4", chatId);

                //send messege

                SendMessage message = createMessage("*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь");

                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Купити Джавелін (50 монет) ", "buy_javelin"
                ));
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("buy_javelin") && getLevel(chatId) == 4) {
                //increase level
               // setLevel(chatId,5);

                //send image
                //send messege

                SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");

                message.setChatId(chatId);

                sendApiMethodAsync(message);
                sendImage("final", chatId);

            }


        }




}


    public Long getChatId(Update update){
        if (update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }

        if (update.hasCallbackQuery()){
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;

        }


    public SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons (SendMessage message, Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()){
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

    }

    public void sendImage(String name, Long chatId){
        SendAnimation animation = new SendAnimation();
        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }


    public int getLevel(Long chatId){
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> variants){
        ArrayList<String> copy = new ArrayList<>(variants);
        Collections.shuffle(copy);
        return copy.subList(0,3);
    }

    }


