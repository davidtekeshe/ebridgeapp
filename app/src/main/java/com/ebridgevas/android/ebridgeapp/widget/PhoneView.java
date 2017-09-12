package com.ebridgevas.android.ebridgeapp.widget;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.util.AndroidUtilities;
import com.ebridgevas.android.ebridgeapp.util.LayoutHelper;
import com.ebridgevas.android.ebridgeapp.util.PhoneFormat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PhoneView extends LinearLayout {

    private EditText codeField;
    private HintEditText phoneField;
    private TextView countryButton;

    private int countryState = 0;

    private ArrayList<String> countriesArray = new ArrayList<>();
    private HashMap<String, String> countriesMap = new HashMap<>();
    private HashMap<String, String> codesMap = new HashMap<>();
    private HashMap<String, String> phoneFormatMap = new HashMap<>();

    private boolean ignoreSelection = false;
    private boolean ignoreOnTextChange = false;
    private boolean ignoreOnPhoneChange = false;
    private boolean nextPressed = false;

    public PhoneView(final Context context) {
        this(context, null, 0);
    }
    public PhoneView(final Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final static String TAG_REGISTRATION_ACTIVITY = "TAG_REGISTRATION_ACTIVITY";

    public PhoneView(final Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);

        setOrientation(VERTICAL);

        countryButton = new TextView(context);
        countryButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        countryButton.setPadding(AndroidUtilities.dp(12), AndroidUtilities.dp(20), AndroidUtilities.dp(12), 0);
        countryButton.setTextColor(0xff212121);
        countryButton.setMaxLines(1);
        countryButton.setSingleLine(true);
        countryButton.setEllipsize(TextUtils.TruncateAt.END);
        countryButton.setGravity(Gravity.LEFT | Gravity.CENTER_HORIZONTAL);
        countryButton.setBackgroundResource(R.drawable.spinner_states);
        addView(countryButton, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, 56, 15, 20, 30, 14));
//        countryButton.setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//
//                        CountrySelectFragment fragment = new CountrySelectFragment();
//
//                        fragment.setCountrySelectFragmentDelegate(
//                            new CountrySelectFragment.CountrySelectFragmentDelegate() {
//
//                                @Override
//                                public void didSelectCountry(String name) {
//                                    selectCountry(name);
//                                    AndroidUtilities.showKeyboard(phoneField);
//                                    phoneField.requestFocus();
//                                    phoneField.setSelection(phoneField.length());
//                                }
//                        });
//
//                        context.startActivity(new Intent(context, CountrySelectFragment.class));

//                        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
//                        CountrySelectFragment countrySelectFragment = new CountrySelectFragment();
////                        Bundle args = new Bundle();
////                        args.putString("TITLE", "Choose A Contry");
////                        args.putString("AVATOR", "back_arrow");
////                        chats.setArguments(args);
//                        transaction.replace(R.id.registrationRoot, countrySelectFragment, TAG_REGISTRATION_ACTIVITY);
//                        transaction.addToBackStack(null);
//                        transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        transaction.commit();
//
//                    }
//                });

        View view = new View(context);
        view.setPadding(AndroidUtilities.dp(12), 0, AndroidUtilities.dp(12), 0);
        view.setBackgroundColor(0xffdbdbdb);
        addView(view, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, 1, 20, -17.5f, 20, 0));

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        addView(linearLayout, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, 0, 20, 20, 0));

        TextView textView = new TextView(context);
        textView.setText("+");
        textView.setTextColor(0xff212121);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        linearLayout.addView(textView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, 20, 0, 20, 0));

        codeField = new EditText(context);
        codeField.setInputType(InputType.TYPE_CLASS_PHONE);
        codeField.setTextColor(0xff212121);
        AndroidUtilities.clearCursorDrawable(codeField);
        codeField.setPadding(AndroidUtilities.dp(10), 0, 0, 0);
        codeField.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        codeField.setMaxLines(1);
        codeField.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        codeField.setImeOptions(EditorInfo.IME_ACTION_NEXT | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        InputFilter[] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(5);
        codeField.setFilters(inputFilters);
        linearLayout.addView(codeField, LayoutHelper.createLinear(55, 36, -9, 0, 16, 0));
        codeField.addTextChangedListener(

                new TextWatcher() {
                     @Override
                     public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                     }

                     @Override
                     public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                     }

                     @Override
                     public void afterTextChanged(Editable editable) {

                         if (ignoreOnTextChange) {
                             ignoreOnTextChange = false;
                             return;
                         }
                         ignoreOnTextChange = true;

                         String text = PhoneFormat.stripExceptNumbers(codeField.getText().toString());
                         codeField.setText(text);
                         if (text.length() == 0) {
                             countryButton.setText("Zimbabwe");
                             phoneField.setHintText(null);
                             countryState = 1;
                         } else {
                             String country;
                             boolean ok = false;
                             String textToSet = null;
                             if (text.length() > 4) {
                                 ignoreOnTextChange = true;
                                 for (int a = 4; a >= 1; a--) {
                                     String sub = text.substring(0, a);
                                     country = codesMap.get(sub);
                                     if (country != null) {
                                         ok = true;
                                         textToSet = text.substring(a, text.length()) + phoneField.getText().toString();
                                         codeField.setText(text = sub);
                                         break;
                                     }
                                 }
                                 if (!ok) {
                                     ignoreOnTextChange = true;
                                     textToSet = text.substring(1, text.length()) + phoneField.getText().toString();
                                     codeField.setText(text = text.substring(0, 1));
                                 }
                             }
                             country = codesMap.get(text);
                             if (country != null) {
                                 int index = countriesArray.indexOf(country);
                                 if (index != -1) {
                                     ignoreSelection = true;
                                     countryButton.setText(countriesArray.get(index));
                                     String hint = phoneFormatMap.get(text);
                                     phoneField.setHintText(hint != null ? hint.replace('X', '–') : null);
                                     countryState = 0;
                                 } else {
                                     countryButton.setText("WrongCountry");
                                     phoneField.setHintText(null);
                                     countryState = 2;
                                 }
                             } else {
                                 countryButton.setText("WrongCountry");
                                 phoneField.setHintText(null);
                                 countryState = 2;
                             }
                             if (!ok) {
                                 codeField.setSelection(codeField.getText().length());
                             }
                             if (textToSet != null) {
                                 phoneField.requestFocus();
                                 phoneField.setText(textToSet);
                                 phoneField.setSelection(phoneField.length());
                             }
                         }
                     }
                }
        );

        codeField.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_NEXT) {
                            phoneField.requestFocus();
                            phoneField.setSelection(phoneField.length());
                            return true;
                        }
                        return false;
                    }
                });

        phoneField = new HintEditText(context);
        phoneField.setInputType(InputType.TYPE_CLASS_PHONE);
        phoneField.setTextColor(0xff212121);
        phoneField.setHintTextColor(0xff979797);
        phoneField.setPadding(5, 0, 0, 0);

        AndroidUtilities.clearCursorDrawable(phoneField);

        phoneField.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        phoneField.setMaxLines(1);
        phoneField.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        phoneField.setImeOptions(EditorInfo.IME_ACTION_NEXT | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        linearLayout.addView(phoneField, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 36));

        phoneField.addTextChangedListener(
                new TextWatcher() {

                      private int characterAction = -1;
                      private int actionPosition;

                      @Override
                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                          if (count == 0 && after == 1) {
                              characterAction = 1;
                          } else if (count == 1 && after == 0) {
                              if (s.charAt(start) == ' ' && start > 0) {
                                  characterAction = 3;
                                  actionPosition = start - 1;
                              } else {
                                  characterAction = 2;
                              }
                          } else {
                              characterAction = -1;
                          }
                      }

                      @Override
                      public void onTextChanged(CharSequence s, int start, int before, int count) {

                      }

                      @Override
                      public void afterTextChanged(Editable s) {
                          if (ignoreOnPhoneChange) {
                              return;
                          }
                          int start = phoneField.getSelectionStart();
                          String phoneChars = "0123456789";
                          String str = phoneField.getText().toString();
                          if (characterAction == 3) {
                              str = str.substring(0, actionPosition) + str.substring(actionPosition + 1, str.length());
                              start--;
                          }
                          StringBuilder builder = new StringBuilder(str.length());
                          for (int a = 0; a < str.length(); a++) {
                              String ch = str.substring(a, a + 1);
                              if (phoneChars.contains(ch)) {
                                  builder.append(ch);
                              }
                          }
                          ignoreOnPhoneChange = true;
                          String hint = phoneField.getHintText();
                          if (hint != null) {
                              for (int a = 0; a < builder.length(); a++) {
                                  if (a < hint.length()) {
                                      if (hint.charAt(a) == ' ') {
                                          builder.insert(a, ' ');
                                          a++;
                                          if (start == a && characterAction != 2 && characterAction != 3) {
                                              start++;
                                          }
                                      }
                                  } else {
                                      builder.insert(a, ' ');
                                      if (start == a + 1 && characterAction != 2 && characterAction != 3) {
                                          start++;
                                      }
                                      break;
                                  }
                              }
                          }
                          phoneField.setText(builder);
                          if (start >= 0) {
                              phoneField.setSelection(start <= phoneField.length() ? start : phoneField.length());
                          }
                          phoneField.onTextChange();
                          ignoreOnPhoneChange = false;
                      }
                });

        phoneField.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                     @Override
                     public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                         if (i == EditorInfo.IME_ACTION_NEXT) {
//                             onNextPressed();
                             return true;
                         }
                         return false;
                     }
                });

        textView = new TextView(context);
        textView.setText(R.string.registrationInstructions);
        textView.setTextColor(0xff757575);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setGravity(Gravity.LEFT);
        textView.setLineSpacing(AndroidUtilities.dp(2), 1.0f);
        addView(textView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.LEFT, 20, 28, 20, 10));

        HashMap<String, String> languageMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().getAssets().open("countries.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(";");
                countriesArray.add(0, args[2]);
                countriesMap.put(args[2], args[0]);
                codesMap.put(args[0], args[2]);
                if (args.length > 3) {
                    phoneFormatMap.put(args[0], args[3]);
                }
                languageMap.put(args[1], args[2]);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(
                countriesArray, new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareTo(rhs);
                    }
                });

        String country = null;

        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                country = telephonyManager.getSimCountryIso().toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (country != null) {
            String countryName = languageMap.get(country);
            if (countryName != null) {
                int index = countriesArray.indexOf(countryName);
                if (index != -1) {
                    codeField.setText(countriesMap.get(countryName));
                    countryState = 0;
                }
            }
        }
        if (codeField.length() == 0) {
            countryButton.setText("Choose Country");
            phoneField.setHintText(null);
            countryState = 1;
        }

        if (codeField.length() != 0) {
            AndroidUtilities.showKeyboard(phoneField);
            phoneField.requestFocus();
            phoneField.setSelection(phoneField.length());
        } else {
            AndroidUtilities.showKeyboard(codeField);
            codeField.requestFocus();
        }
    }

    public void selectCountry(String name) {
        int index = countriesArray.indexOf(name);
        if (index != -1) {
            ignoreOnTextChange = true;
            String code = countriesMap.get(name);
            codeField.setText(code);
            countryButton.setText(name);
            String hint = phoneFormatMap.get(code);
            phoneField.setHintText(hint != null ? hint.replace('X', '–') : null);
            countryState = 0;
        }
    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        if (ignoreSelection) {
//            ignoreSelection = false;
//            return;
//        }
//        ignoreOnTextChange = true;
//        String str = countriesArray.get(i);
//        codeField.setText(countriesMap.get(str));
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

//    @Override
//    public void onNextPressed() {
//        if (nextPressed) {
//            return;
//        }
//        if (countryState == 1) {
//            needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("ChooseCountry", R.string.ChooseCountry));
//            return;
//        } else if (countryState == 2 && !BuildVars.DEBUG_VERSION) {
//            needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("WrongCountry", R.string.WrongCountry));
//            return;
//        }
//        if (codeField.length() == 0) {
//            needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("InvalidPhoneNumber", R.string.InvalidPhoneNumber));
//            return;
//        }
//
//        ConnectionsManager.getInstance().cleanUp();
//        TLRPC.TL_auth_sendCode req = new TLRPC.TL_auth_sendCode();
//        String phone = PhoneFormat.stripExceptNumbers("" + codeField.getBody() + phoneField.getBody());
//        ConnectionsManager.getInstance().applyCountryPortNumber(phone);
//        req.api_hash = BuildVars.APP_HASH;
//        req.api_id = BuildVars.APP_ID;
//        req.sms_type = 0;
//        req.phone_number = phone;
//        req.lang_code = LocaleController.getLocaleString(LocaleController.getInstance().getSystemDefaultLocale());
//        if (req.lang_code.length() == 0) {
//            req.lang_code = "en";
//        }
//
//        final Bundle params = new Bundle();
//        params.putString("phone", "+" + codeField.getBody() + phoneField.getBody());
//        try {
//            params.putString("ephone", "+" + PhoneFormat.stripExceptNumbers(codeField.getBody().toString()) + " " + PhoneFormat.stripExceptNumbers(phoneField.getBody().toString()));
//        } catch (Exception e) {
//            FileLog.e("tmessages", e);
//            params.putString("ephone", "+" + phone);
//        }
//        params.putString("phoneFormated", phone);
//        nextPressed = true;
//        needShowProgress();
//        ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
//                    @Override
//                    public void run(final TLObject response, final TLRPC.TL_error error) {
//                        AndroidUtilities.runOnUIThread(new Runnable() {
//                                                           @Override
//                                                           public void run() {
//                                                               nextPressed = false;
//                                                               if (error == null) {
//                                                                   final TLRPC.TL_auth_sentCode res = (TLRPC.TL_auth_sentCode) response;
//                                                                   params.putString("phoneHash", res.phone_code_hash);
//                                                                   params.putInt("calltime", res.send_call_timeout * 1000);
//                                                                   setPage(1, true, params, false);
//                                                               } else {
//                                                                   if (error.text != null) {
//                                                                       if (error.text.contains("PHONE_NUMBER_INVALID")) {
//                                                                           needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("InvalidPhoneNumber", R.string.InvalidPhoneNumber));
//                                                                       } else if (error.text.contains("PHONE_CODE_EMPTY") || error.text.contains("PHONE_CODE_INVALID")) {
//                                                                           needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("InvalidCode", R.string.InvalidCode));
//                                                                       } else if (error.text.contains("PHONE_CODE_EXPIRED")) {
//                                                                           needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("CodeExpired", R.string.CodeExpired));
//                                                                       } else if (error.text.startsWith("FLOOD_WAIT")) {
//                                                                           needShowAlert(LocaleController.getString("AppName", R.string.AppName), LocaleController.getString("FloodWait", R.string.FloodWait));
//                                                                       } else if (error.code != -1000) {
//                                                                           needShowAlert(LocaleController.getString("AppName", R.string.AppName), error.text);
//                                                                       }
//                                                                   }
//                                                               }
//                                                               needHideProgress();
//                                                           }
//                                                       });
//                    }
//                }, ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin | ConnectionsManager.RequestFlagTryDifferentDc | ConnectionsManager.RequestFlagEnableUnauthorized);
//    }

//    @Override
//    public void onShow() {
//        super.onShow();
//        if (phoneField != null) {
//            if (codeField.length() != 0) {
//                AndroidUtilities.showKeyboard(phoneField);
//                phoneField.requestFocus();
//                phoneField.setSelection(phoneField.length());
//            } else {
//                AndroidUtilities.showKeyboard(codeField);
//                codeField.requestFocus();
//            }
//        }
//    }

//    @Override
//    public String getHeaderName() {
//        return "Your Phone";
//    }
//
//    @Override
//    public void saveStateParams(Bundle bundle) {
//        String code = codeField.getBody().toString();
//        if (code.length() != 0) {
//            bundle.putString("phoneview_code", code);
//        }
//        String phone = phoneField.getBody().toString();
//        if (phone.length() != 0) {
//            bundle.putString("phoneview_phone", phone);
//        }
//    }
//
//    @Override
//    public void restoreStateParams(Bundle bundle) {
//        String code = bundle.getString("phoneview_code");
//        if (code != null) {
//            codeField.setText(code);
//        }
//        String phone = bundle.getString("phoneview_phone");
//        if (phone != null) {
//            phoneField.setText(phone);
//        }
//    }
}
