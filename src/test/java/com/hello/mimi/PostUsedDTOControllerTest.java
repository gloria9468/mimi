package com.hello.mimi;

import com.hello.mimi.standard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class PostUsedDTOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;


/* createPostDTO 와 createPost 할 때, postDTO 가 같은 객체라는 테스트 코드를 짜고 싶음.
    @Test
    public void testPostDTOEquality() throws Exception {
        String postType = "text"; // 예시 postType 설정

        // 기존 System.out을 백업합니다.
        PrintStream originalOut = System.out;

        // System.out 출력을 캡처하기 위한 스트림을 생성합니다.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                PostDTO postDTO = invocation.getArgument(0);
                if (postDTO instanceof PhotoPostDTO) {
                    throw new IllegalStateException("PhotoPostDTO detected! Test interrupted.");

                }
                return null;
            }
        }).when(postService).createPost(any(PostDTO.class));

        // Perform a POST request to /post/create with the specified postType parameter
        mockMvc.perform(post("/post/create")
                        .param("postType", postType)
                        .param("postId", "1")); // 해당 요청에 임의의 postId 를 넣음.
                //.andExpect(MockMvcResultMatchers.status().isInternalServerError()); // 예시로 InternalServerError로 처리
        // System.out 출력을 원래대로 복원합니다.
        System.setOut(originalOut);

        // 캡처한 로그를 문자열로 변환합니다.
        String output = baos.toString();

        // 로그에서 해시코드 값을 추출합니다.
        String createPostDTOHashCode = extractHashCode(output, "createPostDTO hashCode: ");
        String createPostHashCode = extractHashCode(output, "createPost hashCode: ");

        // 두 해시코드가 동일한지 확인합니다.
        assertEquals(createPostDTOHashCode, createPostHashCode, "The hash codes should match, indicating the same instance.");

    }
    private String extractHashCode(String logOutput, String prefix) {
        int startIndex = logOutput.indexOf(prefix) + prefix.length();
        int endIndex = logOutput.indexOf('\n', startIndex);
        return logOutput.substring(startIndex, endIndex).trim();
    }
 */


}