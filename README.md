# Quests plugin (1.20)
This is a simple quests plugin designed as a quest rotating system. This means that when the quest pool is reset, a brand new list of randomized quests are generated for the players to enjoy! This is different from the traditional static quests, and is best used as a daily or weekly rotation.

- Originally written by CodePunisher.
- Maintained & Refactored by Preva1l.

#
![2024-01-28_03 57 55](https://github.com/TrevorMickelson/Quests/assets/70197204/108138d0-442a-4c37-887f-c4576e11a45b)
![2024-01-28_04 01 57](https://github.com/TrevorMickelson/Quests/assets/70197204/fbe827be-2c1b-4956-b611-e5eb5fac3d27)
#
### Getting started
Want to skip all the details and just add/test a quest? Here's examples of a commands you can type to add a quest!

```/quest add emmy crafting emerald_block 1 30 quest.emmy give %player% diamond 1,give %player% apple 1```

```/quest add dirt block_break dirt 15 100 quest.dirt give %player% diamond 1,give %player% apple 1```

After you have added these quests, but sure to execute ```/quest reset``` to refresh the quest active cycle. Now
you can join a quest via ```/quest menu``` and enjoy :D
#
### Commands
Commands are fully configurable in the config, but here's the default format of the commands.

```/quest add <id> <type> <association> <min> <max> <permission> <console-command-rewards>``` adds quest to database, but not to the active quest pool. This will be randomly selected when the reset command is executed.

```/quest delete <id>```

```/quest language <lang>``` per player langauge

```/quest menu``` opens quest menu to join/leave quests

```/quest reset``` resets active quest cycle

![Screenshot 2024-01-28 035427](https://github.com/TrevorMickelson/Quests/assets/70197204/ceb40ce3-cb16-41b2-b372-3cec0962469c)
#
### Signs
Signs can be created by adding [quest] (or whatever the config based configured value is) to the top of any sign to have it act as a data display mechanism that works per player! Here's a simple example :D
![2024-01-28_04 15 56](https://github.com/TrevorMickelson/Quests/assets/70197204/1e893c27-6cb7-42a4-840b-5e9398871ec3)
#
### Multi-instance
To top it all off, this plugin works in a mult-instanced server environment! This means that multiple servers can use it and interact with the same database no problem! But wait! There's more! It also works with redis! This plugin has been designed to use redis as a caching layer, and a message broker to properly balance the syncing of multiple servers.
#
### Multi-language
Mult-language support has been implemented via lang files in the lang folder. By default there is english and german. A player can select their language via ```/quest language <lang>```
#
### PlaceholderAPI
Just when you thought it couldn't get any better, PLACEHOLDER API SUPPORT IS HERE! Here are the following placeholders.

```%quests_current_id%```

```%current_type%```

```%current_associated_object%```

```%current_progress%```

```%current_requirement%```

```%current_active_completed%```

```%current_active_requirement%```
#
### Configs
#### en.yml (default lang)
```
messages:
 # # # # # # # # # #
 # COMMANDS        #
 # # # # # # # # # #
 commands:
  # The primary quest command/aliases
  #
  # If one of these commands are executed
  # the tab completion commands will be
  # the following within this file, not
  # any other language file
  QuestCommands:
     - "quest"
     - "quests"

  # Sub commands
  ResetSubCommand:
   Command: "reset"
   Usage: "reset quest daily cycle"
  AddSubCommand:
    Command: "add"
    Usage: "add new quest"
  DeleteSubCommand:
   Command: "delete"
   Usage: "delete quest"
  MenuSubCommand:
    Command: "menu"
    Usage: "open quest menu"
  LanguageSubCommand:
   Command: "language"
   Usage: "change language command"


  # Quest sub command viewer
  #
  # %1% -> quest primary command
  # %2% -> quest sub command
  # %3% -> quest sub command usage
  QuestSubCommandView: "&a/%1% %2% &8- &f%3%"

  # %subcommands% -> Displays the above
  QuestCommandsViewList:
   - "&7&m-------------------------"
   - "%subcommands%"
   - "&7&m-------------------------"

 # Sign (top line of sign)
 QuestSignConfiguration: '[quest]'

 # ----- ( IMPORTANT INFORMATION ) -----
 # Menu types -> ARE_YOU_SURE_QUEST_DELETE, ARE_YOU_SURE_QUEST_LEAVE
 #               ARE_YOU_SURE_QUEST_SWITCH, ACTIVE_QUESTS_MENU
 # Button types -> NONE, ARE_YOU_SURE_YES, ARE_YOU_SURE_NO, ACTIVE_QUESTS
 # Sounds are optional (just remove from configuration)
 #
 # # # # # # # # # #
 # GENERIC MENU    #
 # # # # # # # # # #
 GenericBackGroundItems:
  1:
   Material: BLACK_STAINED_GLASS_PANE
   Name: "&r"
   Lore: []
   MenuTypes:
    - ARE_YOU_SURE_QUEST_DELETE
    - ARE_YOU_SURE_QUEST_LEAVE
    - ARE_YOU_SURE_QUEST_SWITCH
    - ACTIVE_QUESTS_MENU
   ButtonType: NONE
   Slots: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 41, 42, 43, 44]
   CloseOnClick: false
  2:
   Material: BARRIER
   Name: "&c&lClose"
   Lore:
    - "&7Click to close"
   MenuTypes:
    - ARE_YOU_SURE_QUEST_DELETE
    - ARE_YOU_SURE_QUEST_LEAVE
    - ARE_YOU_SURE_QUEST_SWITCH
    - ACTIVE_QUESTS_MENU
   ButtonType: NONE
   Slots: [40]
   ClickSound: UI_BUTTON_CLICK
   CloseOnClick: true
  3:
   Material: OAK_SIGN
   Name: "&c&lWARNING"
   Lore:
    - "&7This will delete the quest for"
    - "&7good! If this quest is active,"
    - "&7it will be removed from all"
    - "&7players participating!"
   MenuTypes:
    - ARE_YOU_SURE_QUEST_DELETE
   ButtonType: NONE
   Slots: [4]
   CloseOnClick: false
  4:
   Material: OAK_SIGN
   Name: "&c&lWARNING"
   Lore:
    - "&7This will remove you from"
    - "&7your active quest and delete"
    - "&7all related progress!"
   MenuTypes:
    - ARE_YOU_SURE_QUEST_LEAVE
    - ARE_YOU_SURE_QUEST_SWITCH
   ButtonType: NONE
   Slots: [4]
   CloseOnClick: false

 # # # # # # # # # # # # # # #
 # ARE YOU SURE DELETE MENU  #
 # # # # # # # # # # # # # # #
 QuestDeleteAreYouSureMenu:
  Size: 45
  Title: "Are you sure?"
  OpenSound: ITEM_BOOK_PAGE_TURN
  MenuType: ARE_YOU_SURE_QUEST_DELETE
  GuiItems:
   1:
    Material: LIME_WOOL
    Name: "&a&lYes"
    Lore:
     - "&7Click to confirm"
    ButtonType: ARE_YOU_SURE_YES
    Slots: [21]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false
   2:
    Material: RED_WOOL
    Name: "&c&lNo"
    Lore:
     - "&7Click to deny"
    ButtonType: ARE_YOU_SURE_NO
    Slots: [23]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false

 # # # # # # # # # # # # # # #
 # ARE YOU SURE LEAVE MENU   #
 # # # # # # # # # # # # # # #
 QuestLeaveAreYouSureMenu:
  Size: 45
  Title: "Are you sure?"
  OpenSound: ITEM_BOOK_PAGE_TURN
  MenuType: ARE_YOU_SURE_QUEST_LEAVE
  GuiItems:
   1:
    Material: LIME_WOOL
    Name: "&a&lYes"
    Lore:
     - "&7Click to confirm"
    ButtonType: ARE_YOU_SURE_YES
    Slots: [21]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false
   2:
    Material: RED_WOOL
    Name: "&c&lNo"
    Lore:
     - "&7Click to deny"
    ButtonType: ARE_YOU_SURE_NO
    Slots: [23]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false

 # # # # # # # # # # # # # # #
 # ARE YOU SURE SWITCH MENU  #
 # # # # # # # # # # # # # # #
 QuestSwitchAreYouSureMenu:
  Size: 45
  Title: "Are you sure?"
  OpenSound: ITEM_BOOK_PAGE_TURN
  MenuType: ARE_YOU_SURE_QUEST_SWITCH
  GuiItems:
   1:
    Material: LIME_WOOL
    Name: "&a&lYes"
    Lore:
     - "&7Click to confirm"
    ButtonType: ARE_YOU_SURE_YES
    Slots: [21]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false
   2:
    Material: RED_WOOL
    Name: "&c&lNo"
    Lore:
     - "&7Click to deny"
    ButtonType: ARE_YOU_SURE_NO
    Slots: [23]
    ClickSound: UI_BUTTON_CLICK
    CloseOnClick: false

 # # # # # # # # # # # #
 # ACTIVE QUESTS MENU  #
 # # # # # # # # # # # #
 ActiveQuestsMenu:
  Size: 45
  Title: "Active Quests"
  # Menu only has a single item
  #
  # display placeholders
  # %quest_id%, %quest_type%, %quest_associated_object%
  # %quest_current_progress%, %quest_required_progress%
  # %quest_color%, %quest_click_action%
  Name: "%quest_color%&l%quest_id%"
  Lore:
   - "&8Quest Data"
   - ""
   - "%quest_color%Information"
   - "%quest_color%│ &7Type: &f%quest_type%"
   - "%quest_color%│ &7Requirement: &f%quest_associated_object%"
   - "%quest_color%│ &7Progress: &8(&f%quest_current_progress%&7/&f%quest_required_progress%&8)"
   - ""
   - "&8➜ Click to %quest_click_action%"
  JoinSound: UI_BUTTON_CLICK
  LeaveSound: UI_BUTTON_CLICK
  SwitchSound: UI_BUTTON_CLICK
  OpenSound: ITEM_BOOK_PAGE_TURN

 # # # # # # # # # # #
 # GENERIC MESSAGES  #
 # # # # # # # # # # #
 CommandDoesNotExist: "&cThat command does not exist!"
 NoPermission: "&cYou don't have permission to do this!"
 NoConsole: "&cOnly players can execute this command!"
 QuestDeleted: "&aYou have deleted the quest: &2%1%"
 QuestDeletedByAdmin: "&cThe quest you were in has been deleted by an admin!"
 QuestDoesNotExist: "&cThat quest does not exist! Here are all available quests %1%"
 LanguageDoesNotExist: "&cThat language does not exist! Possible options: %1%"
 LanguageChangeSuccess: "&aSuccessfully changed language to &2%1%"
 QuestsResetSuccess: "&aReset and refreshed successfully!"
 QuestsResetToPlayers: "&aQuests have been reset!"
 QuestAddSuccess: "&aQuests successfully added &2%1%"
 InvalidQuestAddUsage: "&cInvalid usage: %1%"
 QuestJoin: "&aYou have joined the quest &2%1%"
 QuestSwitch: "&aYou have switched to the quest &2%1%"
 QuestLeave: "&cYou have left the quest &4%1%"
 QuestsCompleteTopTitle: "&aQuest Complete"
 QuestsCompleteSubTitle: "&fCongratulations!"
 QuestsCompletedAll: "&aYou completed all of the quests!"
 QuestsProgressActionBar: "&aProgress for quest %1% &8(&f%2%&7/&f%3%&8)"
 QuestsBossBar: "&a%1% &8(&f%2%&7/&f%3%&8)"
 QuestSignUpdate: "&aSign successfully added!"

 # Can only have 4 lines
 QuestSign:
  - "&aID &f%1%"
  - "&aType &f%2%"
  - "&aRequire &f%3%"
  - "&aProgress &f%4%"
```

#### config.yml
```
# Which languages should be cached?
#
# The following must be an existing
# lang file in the "lang" folder
#
# WARNING: DO NOT REMOVE THE en.yml
# file! It acts as a default is there
# are no languages configured
lang:
  - en.yml
  - german.yml

# If this is set to true, then when
# a player executes the configured
# quests command, it will automatically
# open the quests' menu.
#
# If false, then all commands will
# be displayed in the chat
DisplayMenuWhenNoArguments: false

# How many quests should be pulled
# from the total quests pull when
# the quests are reset
RandomizedPoolAmount: 3

# Sign
SignCreatePermission: "quests.createsign"

# Quest completion
QuestsCompleteSound: ENTITY_PLAYER_LEVELUP
QuestsCompletedAllRewards:
  - "give %player% diamond 1"

# # # # # # # # # #
# COMMANDS        #
# # # # # # # # # #
commands:
  ResetSubCommand:
    Permission: "quests.reset"
  AddSubCommand:
    Permission: "quests.add"
  DeleteSubCommand:
    Permission: "quests.delete"
  MenuSubCommand:
    Permission: "quests.menu"
  LanguageSubCommand:
    Permission: "quests.language"

# database
mysql:
  Host: localhost
  Port: 3306
  Username: root
  Password: Feel4-Probation6-Untagged2-Slicing1
  Database: test

# redis
redis:
  Host: localhost
  Port: 6379
```

#### server.yml
```
server: test
```
#
### Dependencies
```proton``` this is a hard dependency as it is used for redis message brokering https://www.spigotmc.org/resources/proton-cross-server-plugin-messaging.87159/

```PlaceholderAPI``` this is completely optional https://www.spigotmc.org/resources/placeholderapi.6245/
