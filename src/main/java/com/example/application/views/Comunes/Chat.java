package com.example.application.views.Comunes;


/*
@AnonymousAllowed
@Route(value = "Chat", layout = LayoutPrincipal.class)
@CssImport("./styles/chat-assistant-styles-demo.css")
public class Chat extends VerticalLayout {

    public Chat() {
        ChatAssistant chatAssistant = new ChatAssistant();
        TextArea message = new TextArea();
        message.setLabel("Enter a message from the assistant");
        message.setSizeFull();

        Button chat = new Button("Chat");
        chat.addClickListener(ev -> {
            Message m = Message.builder().content(message.getValue())
                    .sender(Sender.builder().name("Assistant").id("1").avatar("chatbot.png").build()).build();

            chatAssistant.sendMessage(m);
            message.clear();
        });
        chatAssistant.sendMessage(Message.builder().content("Hello, I am here to assist you")
                .sender(Sender.builder().name("Assistant").id("1").avatar("chatbot.png").build()).build());
        chatAssistant.toggle();
        chatAssistant.addChatSentListener(ev -> {
            if (ev.isRight()) {
                Notification.show(ev.getMessage());
            }
        });

        add(message, chat, chatAssistant);
    }
}
*/