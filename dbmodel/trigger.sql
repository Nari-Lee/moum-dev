DELIMITER $$

CREATE TRIGGER after_user_insert
AFTER INSERT ON user
FOR EACH ROW
BEGIN
    INSERT INTO user_achievement (user_id, achievement_id)
    SELECT NEW.id, id FROM achievement;
END$$

DELIMITER ;