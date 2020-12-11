package org.i4di.account.dto.mapper;


import org.i4di.doku.domain.Token;
import org.i4di.account.dto.TokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
@SpringBootApplication
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "category", target = "category")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "expireTime", target = "expireTime",dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    TokenDTO tokenToTokenDTO(Token token);

    List<TokenDTO> tokensToTokenDTOs(List<Token> tokens);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "category", target = "category")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "expireTime", target = "expireTime",dateFormat = Token.ISO_8601_TIMESTAMP_FORMAT)
    Token tokenDTOToToken(TokenDTO tokenDTO);

    List<Token> tokenDTOsToTokens(List<TokenDTO> tokenDTOs);
}
