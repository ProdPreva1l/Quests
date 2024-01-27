package com.codepunisher.quests.commands;

import com.codepunisher.quests.cache.QuestCache;
import com.codepunisher.quests.cache.QuestPlayerCache;
import com.codepunisher.quests.cache.QuestSubCommandCache;
import com.codepunisher.quests.commands.subcommands.*;
import com.codepunisher.quests.database.QuestDatabase;
import com.codepunisher.quests.models.CmdType;
import com.codepunisher.quests.redis.RedisActiveQuests;
import com.codepunisher.quests.redis.RedisPlayerData;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import me.drepic.proton.common.ProtonManager;

@AllArgsConstructor
public class QuestSubCommandRegistrar {
  private final QuestSubCommandCache questSubCommandCache;
  private final QuestCache questCache;
  private final QuestPlayerCache playerCache;
  private final QuestDatabase questDatabase;
  private final RedisActiveQuests redisActiveQuests;
  private final RedisPlayerData redisPlayerData;
  private final ProtonManager proton;
  private final Gson gson;

  public void register() {
    QuestResetSubCommand questsResetSubCommand =
        new QuestResetSubCommand(
            redisActiveQuests, redisPlayerData, questCache, playerCache, proton);
    proton.registerMessageHandlers(questsResetSubCommand);

    QuestsAddSubCommand questsAddSubCommand =
        new QuestsAddSubCommand(questCache, questDatabase, proton, gson);
    proton.registerMessageHandlers(questsAddSubCommand);

    QuestDeleteSubCommand questDeleteSubCommand =
        new QuestDeleteSubCommand(
            questDatabase, redisActiveQuests, questCache, playerCache, proton);
    proton.registerMessageHandlers(questDeleteSubCommand);

    questSubCommandCache.add(CmdType.RELOAD, new QuestsReloadCommand());
    questSubCommandCache.add(CmdType.ADD, questsAddSubCommand);
    questSubCommandCache.add(CmdType.DELETE, questDeleteSubCommand);
    questSubCommandCache.add(CmdType.RESET, questsResetSubCommand);
    questSubCommandCache.add(CmdType.STATUS, new QuestsStatusSubCommand());
    questSubCommandCache.add(CmdType.MENU, new QuestsMenuSubCommand(questCache, playerCache));
    questSubCommandCache.add(CmdType.LANGUAGE, new QuestLanguageSubCommand());
  }
}
