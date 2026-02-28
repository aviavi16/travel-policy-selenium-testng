param(
    [Parameter(ValueFromRemainingArguments = $true)]
    [string[]]$MavenArgs
)

if (-not $MavenArgs -or $MavenArgs.Count -eq 0) {
    $MavenArgs = @("test")
}

# Keep this override local to this process only.
$env:JAVA_TOOL_OPTIONS = ""

$mavenCmd = if (Test-Path ".\mvnw.cmd") { ".\mvnw.cmd" } else { "mvn" }

Write-Host "Running: $mavenCmd $($MavenArgs -join ' ')"
& $mavenCmd @MavenArgs
exit $LASTEXITCODE
