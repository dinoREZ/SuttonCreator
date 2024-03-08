package ${basePackage}.api.states;

public interface ${primaryName}${secondaryName}RelTypes {
    String CREATE = "create${secondaryName}of${primaryName}";
    String GET_ALL_LINKED = "getAll${secondaryName}of${primaryName}";
    String GET_ALL = "getAllLinkable${secondaryName}";
    String UPDATE = "update${secondaryName}of${primaryName}";
    String CREATE_LINK = "link${secondaryName}To${primaryName}";
    String DELETE = "unlink${secondaryName}From${primaryName}";
    String GET = "get${secondaryName}of${primaryName}";
}
