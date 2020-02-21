package randall.common;

public enum ServerPath {
  ;
  public static final String DATABASE = "database";
  public static final String ACCOUNT = "account";
  public static final String CORE = "core";
  public static final String LOGGER = "logger";
  public static final String RUN = "run";
  public static final String ROLE = "role";
  public static final String LOGIN = "login";
  public static final String TOP = "top";

  public static final String TITLE = "Title";
  public static final String SERVER_NAME = "ServerName";
  public static final String DB_NAME = "DBName";
  public static final String DB_DIR = "DBDir";
  public static final String DB_ADDRESS = "DBAddr";
  public static final String DB_PORT = "DBPort";
  public static final String IDS_ADDRESS = "IDSAddr";
  public static final String IDS_PORT = "IDSPort";
  public static final String GATE_ADDRESS = "GateAddr";
  public static final String GATE_PORT = "GatePort";
  public static final String SERVER_ADDRESS = "ServerAddr";
  public static final String SERVER_PORT = "ServerPort";
  public static final String MONITOR_ADDRESS = "MonAddr";
  public static final String MONITOR_PORT = "MonPort";
  public static final String CLOSE_WUXING = "CloseWuXin";
  public static final String ADDRESS_TABLE_FILENAME = "!addrtable.txt";
  public static final String SERVER_TABLE_FILENAME = "!servertable.txt";
  public static final String LOGGER_DIR = "Logger";
  public static final String DATA_DIR = "Data";
  public static final String SHARE = "Share";

  public enum Database {
    ;
    public static final String CONFIG_FILENAME = "Dbsrc.ini";
    public static final String SERVER_INFO_FILENAME = "!serverinfo.txt";
    public static final String USER_NAME_FILTER_FILENAME = "FUserName.txt";
    public static final String USER_NAME_FILTER_HEADER = ";创建人物过滤字符，一行一个过滤";
  }

  public enum Account {
    ;
    public static final String CONFIG_FILENAME = "Logsrv.ini";
    public static final String ID_DIR = "IDDir";
    public static final String LOGGER_DIR = "CountLogDir";
    public static final String SERVER_ADDRESS_FILENAME = "!serveraddr.txt";
    public static final String USER_LIMIT_FILENAME = "!UserLimit.txt";
  }

  public enum Core {
    ;
    public static final String CONFIG_FILENAME = "!Setup.txt";
    public static final String MSG_SERVER_ADDRESS = "MsgSrvAddr";
    public static final String MSG_SERVER_PORT = "MsgSrvPort";
    public static final String LOGGER_SERVER_ADDRESS = "LogServerAddr";
    public static final String LOGGER_SERVER_PORT = "LogServerPort";
    public static final String GUILD_DIR = "GuildDir";
    public static final String GUILD_DIR_VALUE = "GuildBase\\Guilds\\";
    public static final String GUILD_FILE = "GuildFile";
    public static final String GUILD_FILE_VALUE = "GuildBase\\GuildList.txt";
    public static final String CONNECT_LOGGER_DIR = "ConLogDir";
    public static final String CONNECT_LOGGER_DIR_VALUE = "ConLog\\";
    public static final String CASTLE_DIR = "CastleDir";
    public static final String CASTLE_DIR_VALUE = "Castle\\";
    public static final String CASTLE_FILE = "CastleFile";
    public static final String CASTLE_FILE_VALUE = "Castle\\List.txt";
    public static final String GAME_DATA_DIR = "GameDataDir";
    public static final String GAME_DATA_DIR_VALUE = "Envir\\";
    public static final String ENVIR_DIR = "EnvirDir";
    public static final String ENVIR_DIR_VALUE = "Envir\\";
    public static final String MAP_DIR = "MapDir";
    public static final String MAP_DIR_VALUE = "Map\\";
    public static final String NOTICE_DIR = "NoticeDir";
    public static final String NOTICE_DIR_VALUE = "Notice\\";
    public static final String LOGGER_DIR = "LogDir";
    public static final String LOGGER_DIR_VALUE = "Log\\";
    public static final String EMAIL_DIR = "EMailDir";
    public static final String EMAIL_DIR_VALUE = "EMail\\";
    public static final String GUILD_BASE = "GuildBase\\";
  }

  public enum Logger {
    ;
    public static final String CONFIG_FILENAME = "LogData.ini";
    public static final String PORT = "Port";
    public static final String BASE_DIR = "BaseDir";
    public static final String BASE_DIR_VALUE = "BaseDir\\";
  }

  public enum Run {
    ;
    public static final String CONFIG_FILENAME = "Config.ini";
    public static final String CENTER_ADDRESS = "CenterAddr";
    public static final String CENTER_PORT = "CenterPort";
  }

  public enum Role {
    ;
    public static final String CONFIG_FILENAME = "Config.ini";
  }

  public enum Login {
    ;
    public static final String CONFIG_FILENAME = "Config.ini";
  }

  public enum Top {
    ;
    public static final String CONFIG_FILENAME = "Config.ini";
  }
}
