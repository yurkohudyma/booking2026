package ua.hudyma.enums;

public enum TransactionStatus {
    PENDING,        // Очікує обробки
    ON_HOLD,        // Тимчасово призупинена
    PROCESSING,     // В процесі
    SUCCESS,        // Успішно завершена
    DECLINED,       // Відхилена (банком/провайдером)
    FAILED,         // Помилка виконання
    CANCELLED,      // Скасована
    EXPIRED,        // Термін дії минув
    REFUNDED,       // Повернення коштів після успіху
    REVERSED        // Реверс (відкат транзакції)
}
