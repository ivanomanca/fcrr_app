/* Generated By:JavaCC: Do not edit this line. ArtifactManualParserTokenManager.java */
package cartago.manual.parser;
import cartago.*;
import cartago.manual.syntax.*;
import cartago.manual.syntax.UsageProtBody.BodyType;
import cartago.manual.syntax.LogExpr.LogicalOp;
import cartago.manual.syntax.RelExpr.RelationalOp;
import cartago.manual.syntax.ArithExpr.ArithmeticOp;
import java.util.*;
import java.util.logging.*;

/** Token Manager. */
public class ArtifactManualParserTokenManager implements ArtifactManualParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x20000000000000L) != 0L)
            return 17;
         if ((active0 & 0x1b8600L) != 0L)
         {
            jjmatchedKind = 24;
            return 46;
         }
         return -1;
      case 1:
         if ((active0 & 0x1b8600L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 1;
            return 46;
         }
         return -1;
      case 2:
         if ((active0 & 0x1a0000L) != 0L)
            return 46;
         if ((active0 & 0x18600L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 2;
            return 46;
         }
         return -1;
      case 3:
         if ((active0 & 0x8000L) != 0L)
            return 46;
         if ((active0 & 0x10600L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 3;
            return 46;
         }
         return -1;
      case 4:
         if ((active0 & 0x10000L) != 0L)
            return 46;
         if ((active0 & 0x600L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 4;
            return 46;
         }
         return -1;
      case 5:
         if ((active0 & 0x200L) != 0L)
            return 46;
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 5;
            return 46;
         }
         return -1;
      case 6:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 6;
            return 46;
         }
         return -1;
      case 7:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 7;
            return 46;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 32;
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 38:
         return jjStopAtPos(0, 43);
      case 40:
         return jjStopAtPos(0, 37);
      case 41:
         return jjStopAtPos(0, 38);
      case 42:
         jjmatchedKind = 52;
         return jjMoveStringLiteralDfa1_0(0x40000000000000L);
      case 43:
         return jjStopAtPos(0, 35);
      case 44:
         return jjStopAtPos(0, 39);
      case 45:
         return jjStopAtPos(0, 36);
      case 47:
         return jjStartNfaWithStates_0(0, 53, 17);
      case 58:
         return jjMoveStringLiteralDfa1_0(0x3800L);
      case 59:
         return jjStopAtPos(0, 31);
      case 60:
         jjmatchedKind = 44;
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 61:
         jjmatchedKind = 50;
         return jjMoveStringLiteralDfa1_0(0x9000000000000L);
      case 62:
         jjmatchedKind = 46;
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 63:
         return jjStopAtPos(0, 34);
      case 91:
         return jjStopAtPos(0, 40);
      case 92:
         return jjMoveStringLiteralDfa1_0(0x2000000000000L);
      case 93:
         return jjStopAtPos(0, 42);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x100200L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 123:
         return jjStopAtPos(0, 7);
      case 124:
         return jjStopAtPos(0, 41);
      case 125:
         return jjStopAtPos(0, 8);
      case 126:
         return jjStopAtPos(0, 18);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 33:
         if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 33);
         break;
      case 42:
         if ((active0 & 0x40000000000000L) != 0L)
            return jjStopAtPos(1, 54);
         break;
      case 46:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000000L);
      case 61:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 45);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         else if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x10200L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 102:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x120000L);
      case 112:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 46:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStopAtPos(2, 51);
         break;
      case 61:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStopAtPos(2, 49);
         break;
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      case 100:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(2, 20, 46);
         break;
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 116:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(2, 17, 46);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x9000L);
      case 118:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(2, 19, 46);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 46);
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x400L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x200L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x1800L);
      case 101:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 46);
         return jjMoveStringLiteralDfa5_0(active0, 0x400L);
      case 121:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(4, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 108:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(5, 9, 46);
         break;
      case 111:
         return jjMoveStringLiteralDfa6_0(active0, 0x800L);
      case 112:
         return jjMoveStringLiteralDfa6_0(active0, 0x400L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa7_0(active0, 0x800L);
      case 114:
         return jjMoveStringLiteralDfa7_0(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(7, 11);
         break;
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x1400L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(8, 12);
         break;
      case 116:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(8, 10, 46);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 46;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 17:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(23, 24);
                  else if (curChar == 47)
                     jjCheckNAddStates(0, 2);
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 21)
                        kind = 21;
                     jjCheckNAddStates(3, 7);
                  }
                  else if (curChar == 46)
                     jjCheckNAddTwoStates(31, 35);
                  else if (curChar == 47)
                     jjAddStates(8, 9);
                  else if (curChar == 34)
                     jjCheckNAddStates(10, 12);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  break;
               case 46:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAddTwoStates(36, 35);
                  }
                  else if (curChar == 46)
                     jjCheckNAdd(35);
                  break;
               case 1:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(2);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAdd(2);
                  break;
               case 3:
                  if (curChar == 34)
                     jjCheckNAddStates(10, 12);
                  break;
               case 4:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(10, 12);
                  break;
               case 6:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(10, 12);
                  break;
               case 7:
                  if (curChar == 34 && kind > 23)
                     kind = 23;
                  break;
               case 8:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(13, 16);
                  break;
               case 9:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(10, 12);
                  break;
               case 10:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 14:
                  if ((0x3ff000000000000L & l) != 0L && kind > 26)
                     kind = 26;
                  break;
               case 16:
                  if (curChar == 47)
                     jjAddStates(8, 9);
                  break;
               case 18:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 19:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 20:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 21:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 23:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 24:
                  if (curChar == 42)
                     jjCheckNAddStates(17, 19);
                  break;
               case 25:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               case 26:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               case 27:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 30:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(31, 35);
                  break;
               case 31:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAddTwoStates(31, 32);
                  break;
               case 33:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(34);
                  break;
               case 34:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(34);
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStates(36, 35);
                  break;
               case 36:
                  if (curChar == 46)
                     jjCheckNAdd(35);
                  break;
               case 38:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAddStates(3, 7);
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(39);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(40, 41);
                  break;
               case 41:
                  if (curChar == 46)
                     jjCheckNAdd(31);
                  break;
               case 42:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(42, 43);
                  break;
               case 44:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(45);
                  break;
               case 45:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(45);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                  }
                  else if (curChar == 95)
                  {
                     if (kind > 25)
                        kind = 25;
                     jjCheckNAdd(13);
                  }
                  if ((0x7fffffe00000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAddTwoStates(36, 35);
                  }
                  else if ((0x7fffffeL & l) != 0L)
                  {
                     if (kind > 14)
                        kind = 14;
                     jjCheckNAdd(29);
                  }
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(20, 21);
                  break;
               case 46:
               case 35:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStates(36, 35);
                  break;
               case 4:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(10, 12);
                  break;
               case 5:
                  if (curChar == 92)
                     jjAddStates(22, 24);
                  break;
               case 6:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(10, 12);
                  break;
               case 12:
                  if (curChar != 95)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(13);
                  break;
               case 13:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(13);
                  break;
               case 14:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 26)
                     kind = 26;
                  break;
               case 15:
                  if ((0x7fffffe07fffffeL & l) != 0L && kind > 27)
                     kind = 27;
                  break;
               case 18:
                  jjAddStates(0, 2);
                  break;
               case 23:
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 25:
               case 26:
                  jjCheckNAddTwoStates(26, 24);
                  break;
               case 28:
                  if ((0x7fffffeL & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  jjCheckNAdd(29);
                  break;
               case 29:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  jjCheckNAdd(29);
                  break;
               case 32:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(25, 26);
                  break;
               case 37:
                  if ((0x7fffffe00000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStates(36, 35);
                  break;
               case 43:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(27, 28);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(10, 12);
                  break;
               case 18:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(0, 2);
                  break;
               case 23:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 25:
               case 26:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 46 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   18, 19, 21, 39, 40, 41, 42, 43, 17, 22, 4, 5, 7, 4, 5, 9, 
   7, 24, 25, 27, 1, 2, 6, 8, 10, 33, 34, 44, 45, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\173", "\175", 
"\155\141\156\165\141\154", "\165\163\141\147\145\160\162\157\164", "\72\160\162\145\143\157\156\144", 
"\72\146\165\156\143\164\151\157\156", "\72\142\157\144\171", null, "\164\162\165\145", "\146\141\154\163\145", 
"\156\157\164", "\176", "\144\151\166", "\155\157\144", null, null, null, null, null, null, 
null, null, null, null, "\73", "\41", "\41\41", "\77", "\53", "\55", "\50", "\51", 
"\54", "\133", "\174", "\135", "\46", "\74", "\74\75", "\76", "\76\75", "\75\75", 
"\134\75\75", "\75", "\75\56\56", "\52", "\57", "\52\52", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7fffffffffff81L, 
};
static final long[] jjtoSkip = {
   0x7eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[46];
private final int[] jjstateSet = new int[92];
protected char curChar;
/** Constructor. */
public ArtifactManualParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ArtifactManualParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 46; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
