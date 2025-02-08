package calculator;

import camp.nextstep.edu.missionutils.Console;
/*입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
        - 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
        - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.*/
public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 변수 선언
        String stnc;        // 입력값
        int answer = 0;
        boolean hasCustomed = false;
        char customId = ',';      // 커스텀 구분자


        // 문자열 입력받기
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        stnc = Console.readLine();

        int i=0;

        if(stnc.substring(0,2).equals("//") && stnc.substring(3,5).equals("\\n")){
            customId = stnc.charAt(2);
            hasCustomed = true;
            i=5;
        }
        int odr = i;
        int tmp = 1;

        for(; i<stnc.length(); i++){
            boolean condition= stnc.charAt(i) == ',' || stnc.charAt(i) == ':';
            if(hasCustomed){condition = condition || stnc.charAt(i) == customId;}

            if(i==stnc.length()-1){           // 마지막에는 구분자 이후의 숫자를 더함
                for(int j = i; j>=odr; j--){
                    answer += (stnc.charAt(j)-48) * tmp;
                    tmp *= 10;
                }
            }else if(condition){     // , 이나 : 나오면 그 전까지를 answer에 더함
                for(int j = i-1; j>=odr; j--){
                    answer += (stnc.charAt(j)-48) * tmp;
                    tmp *= 10;
                }
                tmp = 1;
                odr = i+1;
            }
        }

        System.out.println("결과 : " + answer);
    }
}
