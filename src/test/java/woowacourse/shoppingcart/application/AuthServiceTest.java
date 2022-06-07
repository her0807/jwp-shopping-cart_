package woowacourse.shoppingcart.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static woowacourse.shoppingcart.dao.CustomerFixture.connieDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import woowacourse.auth.application.AuthService;
import woowacourse.auth.dto.TokenResponse;
import woowacourse.auth.exception.NotFoundException;
import woowacourse.auth.support.JwtTokenProvider;
import woowacourse.shoppingcart.application.dto.AddressResponse;
import woowacourse.shoppingcart.application.dto.CustomerDto;
import woowacourse.shoppingcart.application.dto.SignInDto;

@SpringBootTest
@DisplayName("AuthService 는")
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtTokenProvider provider;

    @BeforeEach
    void setup() {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0");
        jdbcTemplate.update("truncate table orders_detail");
        jdbcTemplate.update("truncate table cart_item");
        jdbcTemplate.update("truncate table orders");
        jdbcTemplate.update("truncate table product");
        jdbcTemplate.update("truncate table customer");
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1");
    }

    @DisplayName("로그인을 할 때")
    @Nested
    class SignInTest {

        @Test
        @DisplayName("로그인이 되면 토큰이 정상적으로 발급된다.")
        void createAccessToken() throws JsonProcessingException {
            코니_회원_가입();
            final String email = "her0807@naver.com";
            final TokenResponse response = authService.signIn(new SignInDto(email, "password1!"));
            final ObjectMapper mapper = new JsonMapper();
            AddressResponse tokenPayloadDto = mapper.readValue(provider.getPayload(response.getAccessToken()),
                    AddressResponse.class);
            assertThat(tokenPayloadDto.getEmail()).isEqualTo(email);
            assertThat(response.getCustomerId()).isNotNull();
        }

        @Test
        @DisplayName("회원 가입을 하지 않은 유저면 에러가 발생한다.")
        void noSignUpCustomer() {
            final String email = "her0807@naver.com";
            assertThatThrownBy(() -> authService.signIn(new SignInDto(email, "password1!")))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("가입하지 않은 유저입니다.");
        }
    }

    private Long 코니_회원_가입() {
        CustomerDto newCustomer = connieDto;
        final Long customerId = customerService.createCustomer(newCustomer);
        return customerId;
    }
}
