protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        callPhone("13726204215");
        // 搭配 CallServie 使用
}

private void callPhone(String inputNumber) {
    @SuppressLint("ServiceCast") TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
    Uri uri = Uri.fromParts("tel", inputNumber, null);
    Bundle extras = new Bundle();
    extras.putBoolean(TelecomManager.EXTRA_START_CALL_WITH_SPEAKERPHONE, false);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

        if (telecomManager.getDefaultDialerPackage().equals(getPackageName())){
            telecomManager.placeCall(uri, extras);
        }
        else{
            Uri phoneNumber = Uri.parse("tel:" + inputNumber);
            Intent callIntent = new Intent(Intent.ACTION_CALL, phoneNumber);
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            currentCallNumber = inputNumber;
            startActivity(callIntent);
        }
    }
    else{
        Toast.makeText(this, "Please allow permission", Toast.LENGTH_SHORT).show();
    }
}
